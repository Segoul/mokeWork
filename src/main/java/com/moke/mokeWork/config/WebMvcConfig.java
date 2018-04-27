package com.moke.mokeWork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.moke.mokeWork.config.interceptor.AccessInterceptor;


/**
 * Web MVC配置
 * @author Segoul
 *
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	//增加拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new AccessInterceptor())    //指定拦截器类
                .addPathPatterns("/hello/**");        //指定该类拦截的url
    }
}
