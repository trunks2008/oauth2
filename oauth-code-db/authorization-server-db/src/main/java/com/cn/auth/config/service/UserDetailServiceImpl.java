package com.cn.auth.config.service;

import com.cn.auth.domain.TbPermission;
import com.cn.auth.domain.TbUser;
import com.cn.auth.service.TbPermissionService;
import com.cn.auth.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-security-oauth2-test
 * @author: Hydra
 * @create: 2021-03-03 16:14
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private TbUserService userService;

    @Autowired
    private TbPermissionService permissionService;

    /**
     * 根据用户名查询用户信息，权限
     * @Param: [userName]
     * @return: org.springframework.security.core.userdetails.UserDetails
     * @Author: Hydra
     * @Date: 2021/3/1
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TbUser tbUser = userService.getUserByUserName(userName);
        if (tbUser==null){
            throw new UsernameNotFoundException("username : "+userName+" is not exist");
        }

        List<GrantedAuthority> authorities=new ArrayList<>();
        //获取用户权限
        List<TbPermission> permissions = permissionService.getByUserId(tbUser.getId());
        permissions.forEach(permission->{
            authorities.add(new SimpleGrantedAuthority(permission.getEname()));
        });
        return new User(tbUser.getUsername(),tbUser.getPassword(),authorities);
    }
}
