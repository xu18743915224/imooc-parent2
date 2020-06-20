package com.imooc.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session过期跳至登录页
 * 01第一步: 创建拦截器(定义拦截器)
 */
@Slf4j
@Component
public class WebMvcInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从session中获取token信息
        String token = (String) request.getSession().getAttribute("token");

        // session过期
        if (token == null) {
            log.info(">>>未登录, 跳至登录页");
            response.sendRedirect("/static/frame/frame1/pages/admin/login.html"); // 通过接口跳转登录页面, 注:重定向后下边的代码还会执行 ; /outToLogin是跳至登录页的后台接口
            return false;
        } else {
            return true;
        }
    }
}
