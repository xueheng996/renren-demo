package com.xiaoxue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所用菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    void update(SysUserEntity userEntity);

    boolean updatePassword(Long userId,String password,String newPassword);

}
