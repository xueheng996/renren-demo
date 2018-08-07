package com.xiaoxue.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaoxue.modules.sys.entity.SysRoleDeptEntity;
import com.xiaoxue.modules.sys.entity.SysRoleEntity;

import java.util.List;


public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Long[] roleIds);

    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    int deletBatch(Long[] roleIds);
}
