package com.imooc.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Author cappuccino
 * @Date 2022-03-05 20:43
 */
@Configuration
public class RedisLockConfiguration {

    @Resource
    private RedisTemplate redisTemplate;

    //@Bean
    //public RedisLock redisLock(){
    //    RedisLock redisLock = new RedisLock(redisTemplate);
    //    return redisLock;
    //}
}
