package com.hou.springboot.api.brand.hou.config;


import com.hou.springboot.api.brand.hou.interceptor.IdempotInterceptor;
import com.hou.springboot.api.brand.hou.interceptor.LoginInterceptor;
import com.hou.springboot.api.brand.hou.resolver.MemberArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


//拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(idempotInterceptor()).addPathPatterns("/**");

    }


//参数解析器
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(memberArgumentResolver());
    }
    @Bean
    public LoginInterceptor loginInterceptor(){

        return new LoginInterceptor();
    }
    @Bean
    public IdempotInterceptor idempotInterceptor(){

        return new IdempotInterceptor();
    }



    @Bean
    public MemberArgumentResolver memberArgumentResolver(){

        return new MemberArgumentResolver();
        }
}
