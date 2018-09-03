package com.xiaoxue.common.config;

import com.xiaoxue.modules.sys.shiro.ShiroTag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class FreemarkerConfig {


    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(ShiroTag shiroTag){
        FreeMarkerConfigurer configurer=new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/templates");
        Map<String,Object> varibles=new HashMap<>(1);
        varibles.put("shiro",shiroTag);
        configurer.setFreemarkerVariables(varibles);

        Properties settting=new Properties();
        settting.setProperty("default_encoding","utf-8");
        settting.setProperty("number_format","0.##");
        configurer.setFreemarkerSettings(settting);
        return configurer;
    }
}
