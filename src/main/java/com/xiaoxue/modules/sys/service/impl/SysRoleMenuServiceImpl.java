package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.modules.sys.dao.SysRoleDeptDao;
import com.xiaoxue.modules.sys.dao.SysRoleMenuDao;
import com.xiaoxue.modules.sys.entity.SysRoleDeptEntity;
import com.xiaoxue.modules.sys.entity.SysRoleMenuEntity;
import com.xiaoxue.modules.sys.service.SysRoleDeptService;
import com.xiaoxue.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {


    @Override
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {

    }

    @Override
    public List<Long> queryDeptIdList(Long roleId) {
        return null;
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return 0;
    }
}
