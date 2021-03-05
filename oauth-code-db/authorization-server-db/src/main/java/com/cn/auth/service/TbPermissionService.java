package com.cn.auth.service;


import com.cn.auth.domain.TbPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hydra
 * @since 2021-03-01
 */
public interface TbPermissionService {

    List<TbPermission> getByUserId(Long userId);

}
