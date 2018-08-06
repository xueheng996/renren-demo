package com.xiaoxue.modules.sys.controller;


import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.sys.shiro.ShiroUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Map;

@Controller
public class SysLoginController extends AbstractController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }


    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public R login(String username,String password){

        try{
            Subject subject=ShiroUtil.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            subject.login(token);
        }catch (UnknownAccountException e){
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e){

        }

        return R.ok();
    }




}
