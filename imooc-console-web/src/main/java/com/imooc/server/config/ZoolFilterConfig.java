package com.imooc.server.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

//通过zuul网关发出去的所有请求都经过ZuulFilter拦截器从session获取token添加到请求头Authorization里

@Component
public class ZoolFilterConfig extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //从session获取token对象
        String token = (String) request.getSession().getAttribute("token");

        if (StringUtils.isNotBlank(token)) {
            requestContext.addZuulRequestHeader("Authorization", "Bearer " + token);
        }

        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

}