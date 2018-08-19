package com.xiaoxue.common.aspect;

import com.xiaoxue.common.annotation.DataFilter;
import com.xiaoxue.common.utils.Contant;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysDeptService;
import com.xiaoxue.modules.sys.service.SysRoleDeptService;
import com.xiaoxue.modules.sys.service.SysUserRoleService;
import com.xiaoxue.modules.sys.shiro.ShiroUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataFileterAspect {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @Pointcut("@annotation(com.xiaoxue.common.annotation.DataFilter)")
    public void dataFilterCut(){

    }
    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point)throws Throwable{
        Object params=point.getArgs()[0];
        if(params!=null&&params instanceof Map){
            SysUserEntity user=ShiroUtil.getUserEntity();
            if(user.getUserId()!=Contant.SUPER_ADMIN){
                Map map=(Map)params;
                map.put(Contant.SQL_FILTER,getSQLFilter(user,point));
            }
        }

    }
    /**
     * 获取过滤数据的SQL
     */
    public String getSQLFilter(SysUserEntity userEntity,JoinPoint point){
        MethodSignature signature=(MethodSignature)point.getSignature();
        DataFilter dataFilter=signature.getMethod().getAnnotation(DataFilter.class);
        //获取表的别名
        String tableAlias=dataFilter.tableAlias();
        if(StringUtils.isNotBlank(tableAlias)){
            tableAlias+=".";
        }

        //部门ID列表
        Set<Long> deptIdList=new HashSet<>();

        //用户角色对应的部门ID列表
        List<Long> roleIdList=sysUserRoleService.queryRoleIdList(userEntity.getUserId());
        if(roleIdList.size()>0){
            List<Long> usreDeptIdList=sysRoleDeptService.queryDeptIdList(roleIdList.toArray(new Long[roleIdList.size()]));
            deptIdList.addAll(usreDeptIdList);
        }

        //用户子部门ID列表
        if(dataFilter.subDept()){
            List<Long> subDeptIdList=sysDeptService.getSubDeptIdList(userEntity.getDeptId());;
            deptIdList.addAll(subDeptIdList);
        }

        StringBuffer sqlFliter=new StringBuffer();
        sqlFliter.append("(");

        if(deptIdList.size()>0){
            sqlFliter.append(tableAlias).append(dataFilter.userId()).append(" in(").append(StringUtils.join(deptIdList,",")).append("）");

        }
        if(dataFilter.user()){
            if(deptIdList.size()>0){
                sqlFliter.append(" or ");
            }
            sqlFliter.append(tableAlias).append(dataFilter.userId()).append("=").append(userEntity.getUserId());
        }
        sqlFliter.append(")");
        return sqlFliter.toString();

    }

}
