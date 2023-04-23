package com.lcj.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//这个类是为了解决跨域访问问题
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")    // /** 表示对所有的路径实行全局跨域访问权限
                        .allowedOrigins("*")    //开放所有ip、端口、域名的访问权限
                        .allowCredentials(true)  //允许发送Cookie信息
                        .allowedMethods("GET", "POST", "PUT", "DELETE")     //开放的Http方法，允许跨域访问
                        .allowedHeaders("*")     //允许HTTP请求中的携带的Header信息
                        .exposedHeaders("*");   //暴露的头部信息
            }
        };
    }
}