package com.imooc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableDiscoveryClient //开启注册中心
@EnableZuulProxy       //开启zuul网关
@SpringBootApplication
public class ImoocConsoleWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImoocConsoleWebApplication.class, args);
    }

}
