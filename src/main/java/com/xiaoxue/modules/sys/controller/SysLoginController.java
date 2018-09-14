package com.xiaoxue.modules.sys.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.xiaoxue.common.utils.R;
import com.xiaoxue.modules.sys.shiro.ShiroUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
public class SysLoginController extends AbstractController {


    @Autowired
    private Producer producer;

    @RequestMapping("captcha.jpg")
    public void  captcha(HttpServletResponse response)throws IOException{

        response.setHeader("Cache-control","no-store,no-cache");
        response.setContentType("image/jpeg");

        String text=producer.createText();
        //生成图片验证码
        BufferedImage image=producer.createImage(text);

        ShiroUtil.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY,text);

        ServletOutputStream outputStream=response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
    }


    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public R login(String username,String password,String captcha){

        String kaptcha=ShiroUtil.getKaptha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equals(kaptcha)){
            return R.error("验证码不正确");
        }

        try{
            Subject subject=ShiroUtil.getSubject();
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return R.error("账号或密码不正确");
        }catch (LockedAccountException e) {
            return R.error("账号已被锁定,请联系管理员");
        }catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }

        return R.ok();
    }

//    /**
//     * 退出
//     */
//    @RequestMapping(value = "logout", method = RequestMethod.GET)
//    public String logout() {
//        ShiroUtils.logout();
//        return "redirect:login.html";
//    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        ShiroUtil.logout();
        return "redirect:login.html";
    }

}
