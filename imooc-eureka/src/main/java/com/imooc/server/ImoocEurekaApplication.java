package com.imooc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ImoocEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImoocEurekaApplication.class, args);
    }

}
