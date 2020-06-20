package com.imooc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //开启注册中心
@EnableFeignClients    //开启Feign
public class ImoocConsoleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImoocConsoleServiceApplication.class, args);
    }

}
