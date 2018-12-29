package com.tiger.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author
 * @create 2018-12-22 19:04
 **/
@SpringBootApplication
@EnableEurekaServer
public class TigerRegistry {
    public static void main(String[] args) {
        SpringApplication.run(TigerRegistry.class);
    }
}
