package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;


public interface SysUserRoleService extends IService<SysUserRoleEntity> {

     void saveOrUpdate(Long userId, List<Long>roleIdList);

     List<Long> queryRoleIdList(Long userId);

     int deleteBatch(Long[] roleIds);

}
