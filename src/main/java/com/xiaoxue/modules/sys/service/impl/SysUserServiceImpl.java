package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.Query;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");

        Page<SysUserEntity> page = this.selectPage(new Page<>(1, 10));

        return new PageUtils(page);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return null;
    }

    @Override
    public void update(SysUserEntity userEntity) {

    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity=new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,new EntityWrapper<SysUserEntity>().eq("user_id",userId).eq("paswword",newPassword));
    }
}
