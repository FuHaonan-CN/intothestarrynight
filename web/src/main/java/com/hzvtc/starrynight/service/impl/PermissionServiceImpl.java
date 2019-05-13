package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.entity.Permission;
import com.hzvtc.starrynight.repository.PermissionRepo;
import com.hzvtc.starrynight.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 权限管理service实现类 .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/4/17 22:31
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepo permissionRepo;

    @Autowired
    public PermissionServiceImpl(PermissionRepo permissionRepo) {
        this.permissionRepo = permissionRepo;
    }


    /**
     * 新增一个权限
     * @param permission .
     * @return Permission
     */
    @Override
    public Permission saveOrUpdate(Permission permission){
        return permissionRepo.save(permission);
    }

    /**
     * 通过id删除权限
     * @param id .
     */
    @Override
    public void deleteById(Long id) {
        permissionRepo.deleteById(id);
    }

    @Override
    public Permission findById(Long id) {
        Optional<Permission> optional = permissionRepo.findById(id);
        return optional.orElse(null);
    }


}
