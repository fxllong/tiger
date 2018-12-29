package com.tiger.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author
 * @create 2018-12-22 19:18
 **/
@EnableZuulProxy
@SpringBootApplication
public class TigerGateway {
    public static void main(String[] args) {
        SpringApplication.run(TigerGateway.class,args);
    }
}
