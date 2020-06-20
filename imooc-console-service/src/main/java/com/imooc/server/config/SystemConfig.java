package com.imooc.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SystemConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    /**
     * 添加拦截器的配置
     *      - /**： 匹配所有路径
     *      - /admin/**：匹配 /admin/ 下的所有路径
     *      - /secure/*：只匹配 /secure/user，不匹配 /secure/user/info
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //1.添加之前自定义拦截器
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")  //2.指定拦截器的url地址
                .excludePathPatterns("/user/login","/register/**");  //3.指定不拦截的url地址
    }
}

