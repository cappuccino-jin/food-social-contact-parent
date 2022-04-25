package com.imooc.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author cappuccino
 * @Date 2022-03-05 20:48
 */
@MapperScan("com.imooc.seckill.mapper")
@SpringBootApplication
public class SeckillApplication {

    public static void main(String[] args){
        SpringApplication.run(SeckillApplication.class, args);
    }

}
