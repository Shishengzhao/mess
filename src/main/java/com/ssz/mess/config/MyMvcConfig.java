package com.ssz.mess.config;

import com.ssz.mess.component.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;
//    注册拦截器
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
//            //注册拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/user/login");
//            }
//
//        };
//        return  webMvcConfigurer;
//    }

    //注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("注册拦截器。。。");
//        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/login.html","/user/login","/");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("login.html").setViewName("login");
        registry.addViewController("main.html").setViewName("admin");
    }
}
