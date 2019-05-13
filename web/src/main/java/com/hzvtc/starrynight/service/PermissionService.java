package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.Permission;

/**
 * 权限管理service .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/4/17 22:31
 */
public interface PermissionService {
    /**
     * 新增一个权限
     * @param permission .
     * @return Permission
     */
    public Permission saveOrUpdate(Permission permission);

    public void deleteById(Long id);

    public Permission findById(Long id);
}
