package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.common.annotation.DataFilter;
import com.xiaoxue.common.utils.Constant;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.dao.SysDeptDao;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.entity.SysDeptEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysDeptService;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {


    @Override
    @DataFilter(subDept = true, user = false)
    public List<SysDeptEntity> queryList(Map<String, Object> map) {

        List<SysDeptEntity> deptList =
                this.selectList(new EntityWrapper<SysDeptEntity>()
                        .addFilterIfNeed(map.get(Constant.SQL_FILTER) != null, (String)map.get(Constant.SQL_FILTER)));

        for(SysDeptEntity sysDeptEntity : deptList){
            SysDeptEntity parentDeptEntity =  this.selectById(sysDeptEntity.getParentId());
            if(parentDeptEntity != null){
                sysDeptEntity.setParentName(parentDeptEntity.getName());
            }
        }
        return deptList;
    }

    @Override
    public List<Long> queryDeptIdList(Long parentId) {
        return baseMapper.queryDeptIdList(parentId);
    }


    @Override
    public List<Long> getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList=new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList=queryDeptIdList(deptId);
        getDeptTreeList(subIdList,deptIdList);
        return deptIdList;
    }

    private void getDeptTreeList(List<Long> subIdList,List<Long> deptIdList){

        for (Long deptId:subIdList){
            List<Long> list=queryDeptIdList(deptId);
            if(list.size()>0){
                getDeptTreeList(list,deptIdList);
            }
            deptIdList.add(deptId);
        }
    }



}
