package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.common.utils.ValidatorUtils;
import com.xiaoxue.modules.sys.entity.SysMenuEntity;
import com.xiaoxue.modules.sys.entity.SysRoleEntity;
import com.xiaoxue.modules.sys.service.SysMenuService;
import com.xiaoxue.modules.sys.service.SysRoleDeptService;
import com.xiaoxue.modules.sys.service.SysRoleMenuService;
import com.xiaoxue.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String ,Object>params){
        PageUtils page=sysRoleService.queryPage(params);

        return R.ok().put("page",page);
    }

    @RequestMapping("/select")
    public R select(){
        List<SysRoleEntity> list=sysRoleService.selectList(null);

        return R.ok().put("list",list);
    }
    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    public R info(@PathVariable("roleId") Long roleId){

        SysRoleEntity roleEntity=sysRoleService.selectById(roleId);
        //查询角色对应的菜单
        List<Long> menuIdList=sysRoleMenuService.queryMenuIdList(roleId);
        roleEntity.setMenuIdList(menuIdList);

        //查询角色对应的部门
        List<Long> deptIdList=sysRoleDeptService.queryDeptIdList(new Long[]{roleId});
        roleEntity.setDeptIdList(deptIdList);

        return R.ok().put("role",roleEntity);
    }

    /**
     * 保存角色
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysRoleEntity role){
        ValidatorUtils.validateEntity(role);

        sysRoleService.save(role);

        return R.ok();
    }

    /**
     * 修改角色
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysRoleEntity roleEntity){
        ValidatorUtils.validateEntity(roleEntity);

        sysRoleService.update(roleEntity);

        return R.ok();
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] roleIds){
        sysRoleService.deleteBatch(roleIds);

        return R.ok();
    }
}
