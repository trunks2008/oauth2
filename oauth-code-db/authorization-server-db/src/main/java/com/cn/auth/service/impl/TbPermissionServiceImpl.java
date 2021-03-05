package com.cn.auth.service.impl;

import com.cn.auth.domain.TbPermission;
import com.cn.auth.mapper.TbPermissionMapper;
import com.cn.auth.service.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hydra
 * @since 2021-03-01
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService {

    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> getByUserId(Long userId) {
        return tbPermissionMapper.queryByUserId(userId);
    }
}
