package com.imooc.feeds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author cappuccino
 * @Date 2022-03-28 21:33
 */
@MapperScan("com.imooc.feeds.mapper")
@SpringBootApplication
public class FeedsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedsApplication.class, args);
    }
}
