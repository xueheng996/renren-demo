package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.exception.RRException;
import com.xiaoxue.common.utils.Constant;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {


    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 导航菜单
     */
    @RequestMapping("/nav")
    @ResponseBody
    public R nav(){

        List<SysMenuEntity> menuList=sysMenuService.getUserMenuList(getUserId());

        return R.ok().put("menuList",menuList);
    }
    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SysMenuEntity> list(){
        List<SysMenuEntity> menuList=sysMenuService.selectList(null);
        for (SysMenuEntity sysMenuEntity:menuList){
            SysMenuEntity parentMenuEntity=sysMenuService.selectById(sysMenuEntity.getParentId());
            if(parentMenuEntity!=null){
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }
        return menuList;
    }

    /**
     * 选择菜单（添加、修改菜单）
     *
     */
    @RequestMapping("/select")
    @ResponseBody
    public R select(){
        List<SysMenuEntity> menuList=sysMenuService.queryNotButtonList();

        SysMenuEntity root=new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList",menuList);
    }
    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @ResponseBody
    public R info(@PathVariable("menuId")Long menuId){
        SysMenuEntity menu=sysMenuService.selectById(menuId);
        return R.ok().put("menu",menu);
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody SysMenuEntity menu){
        verifyForm(menu);

        sysMenuService.insert(menu);

        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody SysMenuEntity menu){
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(Long menuId){

        if(menuId<=31){
            return R.error("系统菜单，不能删除");
        }
        List<SysMenuEntity> menuList=sysMenuService.queryListParentId(menuId);
        if(menuList.size()>0){
            return R.error("请先删除子菜单或按钮");
        }
        sysMenuService.delete(menuId);

        return R.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menuEntity){
        if(StringUtils.isBlank(menuEntity.getName())){
            throw new RRException("菜单名称不能为空");
        }
        if(menuEntity.getParentId()==null){
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if(menuEntity.getType()==Constant.MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menuEntity.getUrl())){
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType=Constant.MenuType.CATALOG.getValue();
        if(menuEntity.getParentId()!=0){
            SysMenuEntity parentMenu=sysMenuService.selectById(menuEntity.getParentId());
            parentType=parentMenu.getType();
        }
        //目录、菜单
        if(menuEntity.getType()==Constant.MenuType.CATALOG.getValue()||menuEntity.getType()==Constant.MenuType.CATALOG.getValue()){
            if(parentType!=Constant.MenuType.CATALOG.getValue()){
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }
        //按钮
        if (menuEntity.getType()==Constant.MenuType.BUTTON.getValue()){
            if(parentType!=Constant.MenuType.MENU.getValue()){
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }



}
