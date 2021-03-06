package com.flymegoc.cc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://192.168.1.97")
//                .allowedMethods("GET", "POST")
                .allowCredentials(true).maxAge(3600);
    }
}