package com.cn.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.auth.domain.TbUser;
import com.cn.auth.mapper.TbUserMapper;
import com.cn.auth.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hydra
 * @since 2021-03-01
 */
@Service
public class TbUserServiceImpl  implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getUserByUserName(String userName) {
        return  tbUserMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername,userName));
    }
}
