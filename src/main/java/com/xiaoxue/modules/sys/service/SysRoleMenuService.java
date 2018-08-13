package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.modules.sys.entity.SysRoleDeptEntity;
import com.xiaoxue.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;


public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

   void saveOrUpdate(Long roleId, List<Long> menuIdList);

   List<Long> queryMenuIdList(Long roleId);

   int deleteBatch(Long[] roleIds);

}
