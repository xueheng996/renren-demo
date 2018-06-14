package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/info")
    @ResponseBody
    public SysUserEntity getInfo() {
        //return null;
        return sysUserService.selectById(1);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        logger.info("params=" + params);
        PageUtils pageUtils = sysUserService.queryPage(params);
        return R.ok().put("page", pageUtils);
    }


    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String user() {
        return "modules/user";
    }


}
