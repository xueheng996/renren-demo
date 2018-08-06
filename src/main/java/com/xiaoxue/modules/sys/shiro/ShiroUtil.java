package com.xiaoxue.modules.sys.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
}
