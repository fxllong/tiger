package com.tiger.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author
 * @date 2018/9/13
 */
@SpringBootApplication
@MapperScan("com.tiger.user.mapper")
public class TigerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TigerUserApplication.class);
    }
}
