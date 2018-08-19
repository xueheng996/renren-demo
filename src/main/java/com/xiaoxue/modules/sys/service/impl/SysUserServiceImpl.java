package com.xiaoxue.modules.sys.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.Query;
import com.xiaoxue.modules.sys.dao.SysUserDao;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysDeptService;
import com.xiaoxue.modules.sys.service.SysUserRoleService;
import com.xiaoxue.modules.sys.service.SysUserService;
import com.xiaoxue.modules.sys.shiro.ShiroUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;


    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());

        String salt=RandomStringUtils.randomAlphabetic(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtil.sha256(user.getPassword(),user.getSalt()));
        this.insert(user);

        sysUserRoleService.saveOrUpdate(user.getUserId(),user.getRoleIdList());
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");

        Page<SysUserEntity> page = this.selectPage(new Page<>(1, 10));

        return new PageUtils(page);
    }



    @Override
    public void update(SysUserEntity userEntity) {
        if(StringUtils.isBlank(userEntity.getPassword())){
            userEntity.setPassword(null);
        }else {
            userEntity.setPassword(ShiroUtil.sha256(userEntity.getPassword(),userEntity.getSalt()));
        }
        this.updateById(userEntity);

        sysUserRoleService.saveOrUpdate(userEntity.getUserId(),userEntity.getRoleIdList());
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity=new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,new EntityWrapper<SysUserEntity>().eq("user_id",userId).eq("paswword",newPassword));
    }
}
