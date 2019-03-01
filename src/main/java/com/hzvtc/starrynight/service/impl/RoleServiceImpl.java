package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.comm.config.JwtUtils;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.RoleRepo;
import com.hzvtc.starrynight.repository.UserRepo;
import com.hzvtc.starrynight.service.RoleService;
import com.hzvtc.starrynight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    /**
     * 新增一个用户
     * @Param: User
     */
    @Override
    public void saveOrUpdate(Role role) {
        roleRepo.save(role);
    }

    /**
     * 根据name删除一个用户
     * @Param: name
     */
    @Override
    public void deleteById(Long id){
        roleRepo.deleteById(id);
    }

    @Override
    public Page<Role> findRolesByKey(int page, int pageSize, String key) {
        return roleRepo.findAllByRoleNameLike("%"+key+"%", PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));
    }


}