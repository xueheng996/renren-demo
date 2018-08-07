package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.dao.SysRoleDao;
import com.xiaoxue.modules.sys.dao.SysRoleDeptDao;
import com.xiaoxue.modules.sys.entity.SysRoleDeptEntity;
import com.xiaoxue.modules.sys.entity.SysRoleEntity;
import com.xiaoxue.modules.sys.service.SysRoleDeptService;
import com.xiaoxue.modules.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDeptEntity> implements SysRoleDeptService {


    @Override
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {

    }

    @Override
    public List<Long> queryDeptIdList(Long[] roleIds) {
        return null;
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return 0;
    }
}
