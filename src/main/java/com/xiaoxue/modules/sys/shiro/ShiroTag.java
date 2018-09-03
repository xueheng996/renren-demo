package com.xiaoxue.modules.sys.shiro;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

@Component
public class ShiroTag {


    public boolean hasPermission(String permission) {
        Subject subject=SecurityUtils.getSubject();
        return subject!=null && subject.isPermitted(permission);
    }
}
