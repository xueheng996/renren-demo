package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.modules.sys.dao.SysRoleDeptDao;
import com.xiaoxue.modules.sys.dao.SysRoleMenuDao;
import com.xiaoxue.modules.sys.entity.SysRoleDeptEntity;
import com.xiaoxue.modules.sys.entity.SysRoleMenuEntity;
import com.xiaoxue.modules.sys.service.SysRoleDeptService;
import com.xiaoxue.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        deleteBatch(new Long[]{roleId});

        if(menuIdList.size()==0){
            return;
        }
        List<SysRoleMenuEntity> list=new ArrayList<>(menuIdList.size());
        for(Long menuId:menuIdList){
            SysRoleMenuEntity sysRoleMenuEntity=new SysRoleMenuEntity();
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);

            list.add(sysRoleMenuEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }


    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
