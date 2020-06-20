package com.imooc.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * session过期跳至登录页
 * 02第二步: 配置拦截规则也可以这样定义
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 注入第一步定义好的拦截器
     */
    @Autowired
    private WebMvcInterceptor webMvcInterceptor;

    /**
     * 定义拦截规则, 根据自己需要进行配置拦截和排除的接口
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webMvcInterceptor)
                // .addPathPatterns() 是配置需要拦截的路径
                .addPathPatterns("/**")
                // .excludePathPatterns() 用于排除拦截
                .excludePathPatterns("/") // 排除127.0.0.1进入登录页
                //.excludePathPatterns("/code") // 排除登录页获取验证码接口
                //.excludePathPatterns("/loginVerify") // 排除验证账号密码接口
                .excludePathPatterns("/static/frame/frame1/pages/admin/login.html")   //登录页面
                .excludePathPatterns("/saveSession") //保存session请求
                .excludePathPatterns("/static/res/**")
                .excludePathPatterns("/static/scripts/**")
                .excludePathPatterns("/static/frame/frame1/js/**")
                .excludePathPatterns("/static/frame/frame1/res/**");
    }
}
