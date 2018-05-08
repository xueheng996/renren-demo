package com.xiaoxue.modules.controller;

import com.xiaoxue.modules.entity.SysUserEntity;
import com.xiaoxue.modules.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/info")
    public SysUserEntity getInfo(){
        //return null;
        return sysUserService.selectById(1);
    }


}
