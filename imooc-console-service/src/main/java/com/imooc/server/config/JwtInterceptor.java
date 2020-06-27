package com.imooc.server.config;

import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    /**
     * 简化获取token数据的代码编写（判断是否登录）
     *  1.通过request获取请求token信息
     *  2.从token中解析获取claims
     *  3.将claims绑定到request域中
     */

    private JwtTokenUtil jwtUtils = new JwtTokenUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        // 1.通过request获取请求token信息
        String authorization = request.getHeader("Authorization");
        //判断请求头信息是否为空，或者是否已Bearer开头
        if(!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            //获取token数据
            String token = authorization.replace("Bearer ","");
            //解析token获取claims
            Claims claims = jwtUtils.parseJwt(token);
            //将当前claims绑定到request域
            if(claims != null) {
                //权限校验
                //通过claims获取到当前用户的可访问API权限字符串
                String apis = (String) claims.get("apis");  //例：api-user-delete,api-user-update
                //通过handler
                HandlerMethod h = (HandlerMethod) handler;
                //获取接口上的reqeustmapping注解
                RequestMapping annotation = (RequestMapping) h.getMethodAnnotation(DeleteMapping.class);
                if(annotation!=null){
                    //获取当前请求接口中的name属性的值(即当前接口的权限名)
                    String name = annotation.name();
                    //判断当前用户是否具有相应的请求权限
                    if(StringUtils.isNotEmpty(name)){
                        if(apis.contains(name)) {
                            request.setAttribute("user_claims", claims);
                            return true;
                        }else {
                            throw new CommonServiceException(1001, "访问失败，无权访问！!");
                        }
                    }
                }
                request.setAttribute("user_claims", claims);
                return true;
            }
        }
        throw new CommonServiceException(1001, "访问失败，无权访问！!");
    }
}

