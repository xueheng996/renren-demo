package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.entity.SysRoleDeptEntity;
import com.xiaoxue.modules.sys.entity.SysRoleEntity;

import java.security.acl.LastOwnerException;
import java.util.List;
import java.util.Map;


public interface SysRoleDeptService extends IService<SysRoleDeptEntity> {

   void saveOrUpdate(Long roleId, List<Long> deptIdList);

   List<Long> queryDeptIdList(Long[] roleIds);

   int deleteBatch(Long[] roleIds);

}
