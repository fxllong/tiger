package com.tiger.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author fanxinglong
 * @create 2018-12-29 14:17
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.tiger.cms.mapper")
public class TigerCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TigerCmsApplication.class);
    }



}
