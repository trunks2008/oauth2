package com.cn.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @program: spring-security-oauth2-test
 * @author: Hydra
 * @create: 2021-03-03 16:08
 **/
@Configuration
@EnableAuthorizationServer //开启认证服务
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;    //认证管理

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;//密码加密方式

    @Autowired
    private DataSource dataSource;  // 注入数据源

    @Autowired
    private UserDetailsService userDetailsService; //自定义用户身份认证

    @Bean
    public ClientDetailsService jdbcClientDetailsService(){
        //将client信息存储在数据库中
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore(){
        //对token进行持久化存储在数据库中，数据存储在oauth_access_token和oauth_refresh_token
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //加入对授权码模式的支持
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //设置客户端的配置从数据库中读取，存储在oauth_client_details表
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())//token存储方式
                .authenticationManager(authenticationManager)// 开启密码验证，来源于 WebSecurityConfigurerAdapter
                .userDetailsService(userDetailsService)// 读取验证用户的信息
                .authorizationCodeServices(authorizationCodeServices())
                .setClientDetailsService(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //  配置Endpoint,允许请求，不被Spring-security拦截
        security.tokenKeyAccess("permitAll()") // 开启/oauth/token_key 验证端口无权限访问
                .checkTokenAccess("isAuthenticated()") // 开启/oauth/check_token 验证端口认证权限访问
                .allowFormAuthenticationForClients()// 允许表单认证
                .passwordEncoder(passwordEncoder);   // 配置BCrypt加密
    }
}
