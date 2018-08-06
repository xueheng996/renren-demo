package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.entity.SysRoleEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;

import java.util.Map;


public interface SysRoleService extends IService<SysRoleEntity> {

   PageUtils queryPage(Map<String,Object> params);

   void save(SysRoleEntity role);

   void update(SysRoleEntity role);

   void deleteBatch(Long[] roleIds);

}
