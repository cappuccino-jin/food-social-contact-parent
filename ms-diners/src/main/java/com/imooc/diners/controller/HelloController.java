package com.imooc.diners.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cappuccino
 * @Date 2022-01-12 21:58
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping
    public String hello(String name) {
        return "hello" + name;
    }

}
