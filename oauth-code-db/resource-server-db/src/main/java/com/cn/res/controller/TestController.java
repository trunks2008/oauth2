package com.cn.res.controller;

import com.cn.res.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-security-oauth2-test
 * @author: Hydra
 * @create: 2021-03-03 14:09
 **/
@RestController
public class TestController {
    @GetMapping("/user/{name}")
    public User user(@PathVariable String name){
        return new User(name, 20);
    }
}
