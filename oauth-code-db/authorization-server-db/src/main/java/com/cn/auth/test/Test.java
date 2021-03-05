package com.cn.auth.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @program: spring-security-oauth2-test
 * @author: Hydra
 * @create: 2021-03-03 16:57
 **/
public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode("123456");
        String encode = encoder.encode("client-secret");
        System.out.println(encode);
    }
}
