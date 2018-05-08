package com.xiaoxue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.xiaoxue.modules.*.dao"})
public class RenrenDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RenrenDemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RenrenDemoApplication.class);
    }
}
