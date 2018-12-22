package com.tiger.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author
 * @date 2018/9/13
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.tiger.service.mapper")
public class TigerItemService {

    public static void main(String[] args) {
        SpringApplication.run(TigerItemService.class);
    }
}
