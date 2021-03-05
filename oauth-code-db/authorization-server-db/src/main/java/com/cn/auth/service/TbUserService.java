package com.cn.auth.service;


import com.cn.auth.domain.TbUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hydra
 * @since 2021-03-01
 */
public interface TbUserService {

    TbUser getUserByUserName(String userName);

}
