package com.tiger.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author
 * @date 2018/9/13
 */
@SpringBootApplication
@MapperScan("com.tiger.item.mapper")
public class TigerItemService {

    public static void main(String[] args) {
        SpringApplication.run(TigerItemService.class);
    }
}
