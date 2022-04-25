package com.imooc.restaurants;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author cappuccino
 * @Date 2022-04-17 22:55
 */
@MapperScan("com.imooc.restaurants.mapper")
@SpringBootApplication
public class RestaurantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsApplication.class, args);
    }

}
