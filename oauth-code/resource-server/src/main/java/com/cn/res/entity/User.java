package com.cn.res.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: spring-security-oauth2-test
 * @author: Hydra
 * @create: 2021-03-03 14:09
 **/
@Data
@AllArgsConstructor
public class User {
    private String name;
    private int age;
}
