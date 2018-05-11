package com.xiaoxue.common.xss;


import com.xiaoxue.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

public class SQLFilter {

    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        str=StringUtils.replace(str,"'","");
        str=StringUtils.replace(str,"\"","");
        str=StringUtils.replace(str,";","");
        str=StringUtils.replace(str,"\\","");

        str=str.toLowerCase();

        String[] keywords={};

        for (String keyword:keywords){
            if (str.indexOf(keyword)!=-1){
                throw new RRException("包含非法字符");
            }
        }
        return str;
    }
}
