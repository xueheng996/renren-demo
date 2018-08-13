package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.utils.Assert;
import com.xiaoxue.common.utils.PageUtils;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.common.utils.ValidatorUtils;
import com.xiaoxue.modules.sys.entity.SysUserEntity;
import com.xiaoxue.modules.sys.service.SysUserService;
import com.xiaoxue.modules.sys.shiro.ShiroUtil;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    /**
     * 修改登录用户密码
     */
    @RequestMapping("/password")
    public R password(String password,String newPassword){

        Assert.isBlank(newPassword,"新密码不能为空");
        password=ShiroUtil.sha256(password,getUser().getSalt());

        newPassword=ShiroUtil.sha256(newPassword,getUser().getSalt());

        boolean flag=sysUserService.updatePassword(getUserId(),password,newPassword);
        if(!flag){
            return R.error("原交易密码不正确！");
        }
        return R.ok();
    }
    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    public R inf(@PathVariable("userId")Long userId){
        SysUserEntity user=sysUserService.selectById(userId);

        return R.ok().put("user",user);
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysUserEntity user){
        ValidatorUtils.validateEntity(user);

        sysUserService.update(user);

        return R.ok();
    }
    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] userIds){
        if(ArrayUtils.contains(userIds,1L)){
            return R.error("系统管理员不能删除");
        }
        if(ArrayUtils.contains(userIds,getUserId())){
            return R.error("当前用户不能删除");
        }
        sysUserService.deleteBatchIds(Arrays.asList(userIds));

        return R.ok();
    }





}
