package com.xiaoxue.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.entity.SysUserRoleEntity;

import java.util.List;


public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    int deleteBatch(Long[] roleIds);

}
