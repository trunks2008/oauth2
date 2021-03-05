package com.cn.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.auth.domain.TbPermission;

import java.util.List;

/**
 * @author Hydra
 * @since 2021-03-01
 */
public interface TbPermissionMapper extends BaseMapper<TbPermission> {

    List<TbPermission> queryByUserId(Long userId);

}
