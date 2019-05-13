package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.repository.RoleRepo;
import com.hzvtc.starrynight.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    /**
     * 新增一个角色
     * @param role .
     */
    @Override
    public Role saveOrUpdate(Role role) {
        if (isEmpty(role.getId())){
            return roleRepo.save(role);
        } else {
            Role role1 = findById(role.getId());
            role1.setRoleName(role.getRoleName());
            role1.setRoleDesc(role.getRoleDesc());
            role1.setUsers(role.getUsers());
            return roleRepo.save(role1);
        }
    }

    /**
     * 根据name删除一个用户
     * @Param: name
     */
    @Override
    public void deleteById(Long id){
        roleRepo.deleteById(id);
    }

    /**
     * 根据id查找一个用户
     * @param id .
     */
    @Override
    public Role findById(Long id) {
        Optional<Role> role = roleRepo.findById(id);
        return role.orElse(null);
    }

    @Override
    public Role findByRoleName(String name) {
        return roleRepo.findByRoleName(name);
    }


    @Override
    public Set<Role> findRolesByPId(Long id) {
        return null;
    }

    @Override
    public Page<Role> findRolesByKey(int page, int pageSize, String key) {
        return roleRepo.findAllByRoleNameLike("%"+key+"%", PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));
    }

}