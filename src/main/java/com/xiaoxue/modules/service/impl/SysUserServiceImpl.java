package com.xiaoxue.modules.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoxue.modules.dao.SysUserDao;
import com.xiaoxue.modules.entity.SysUserEntity;
import com.xiaoxue.modules.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {


}
