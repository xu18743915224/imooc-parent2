package com.imooc.server.controller;

import com.imooc.server.common.BaseResponse;
import com.imooc.server.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by: xwl
 **/
@Slf4j
@RestController
@RequestMapping("/")
public class AdminController {

    @RequestMapping("/saveSession")
    public BaseResponse callback(@RequestParam String token, HttpServletRequest request) {
        request.getSession().setAttribute("token", token);
        log.info("token-->"+token);
        return BaseResponse.success();
    }

    @RequestMapping("/logout")
    public BaseResponse logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return BaseResponse.success();
    }

    @RequestMapping("/noTokenToLogin")
    public BaseResponse noTokenToLogin(HttpServletRequest request, HttpServletResponse response) {
        JwtTokenUtil jwtUtils = new JwtTokenUtil();
        // 从session中获取token信息
        String token = (String) request.getSession().getAttribute("token");
        //token不为空claims为null说明token已经过期跳转页面重新登录
        if(StringUtils.isNotBlank(token)) {
            //解析token获取claims
            Claims claims = jwtUtils.parseJwt(token);
            if(claims == null){
                return BaseResponse.timeOutLogin();
            }
        }
        return BaseResponse.success();
    }
}
