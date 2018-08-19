package com.xiaoxue.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xiaoxue.modules.sys.entity.SysUserEntity;

import java.security.acl.LastOwnerException;
import java.util.List;


public interface SysUserDao extends BaseMapper<SysUserEntity> {

    List<String> queryAllPerms(Long userId);

    List<Long> queryAllMenuId(Long userId);
}
