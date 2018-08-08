package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysMenuService;
import com.xiaoxue.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {


    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 导航菜单
     */
    @RequestMapping("/nav")
    public R nav(){

        List<SysMenuEntity> menuList=sysMenuService.getUserMenuList((long) 1);
        return R.ok().put("menuList",menuList);
    }
    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    public R info(@PathVariable("menuId")Long menuId){
        SysMenuEntity menu=sysMenuService.selectById(menuId);
        return R.ok().put("menu",menu);
    }


}
