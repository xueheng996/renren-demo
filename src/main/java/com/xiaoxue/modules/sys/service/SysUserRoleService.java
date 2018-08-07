package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.entity.SysUserEntity;

import java.util.Map;


public interface SysUserRoleService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
