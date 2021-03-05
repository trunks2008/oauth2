package com.cn.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @program: spring-security-oauth2-test
 * @author: Hydra
 * @create: 2021-03-03 11:21
 **/
@Configuration
@EnableAuthorizationServer //开启认证服务
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")//客户端 client_id
                .secret(passwordEncoder.encode("client-secret"))//客户端 secret
                .authorizedGrantTypes("authorization_code")//授权类型，授权码
                .scopes("app-read") //作用域
                .resourceIds("resource1") //资源id
                .redirectUris("http://127.0.0.1:8848/nacos");//重定向地址
    }

    //检查token的策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //资源服务器认证时使用
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");

        //允许表单认证
        security.allowFormAuthenticationForClients();
    }

}
