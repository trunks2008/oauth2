package com.cn.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: spring-security-oauth2-test
 * @author: Hydra
 * @create: 2021-03-03 16:00
 **/
@SpringBootApplication
@MapperScan("com.cn.auth.mapper")
public class AuthServerDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerDbApplication.class,args);
    }
}
