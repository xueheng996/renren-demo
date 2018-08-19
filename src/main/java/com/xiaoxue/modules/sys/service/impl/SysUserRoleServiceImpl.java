package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.common.utils.MapUtils;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.dao.SysUserRoleDao;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.entity.SysUserRoleEntity;
import com.xiaoxue.modules.sys.service.SysUserRoleService;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {


    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        this.deleteByMap(new MapUtils().put("user_id",userId));

        if(roleIdList==null||roleIdList.size()==0){
            return;
        }

        //保存用户与角色关系
        List<SysUserRoleEntity> list=new ArrayList<>(roleIdList.size());
        for (Long roleId:roleIdList){
            SysUserRoleEntity sysUserRoleEntity=new SysUserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);

            list.add(sysUserRoleEntity);
        }
        this.insertBatch(list);

    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
