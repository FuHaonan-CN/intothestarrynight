package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.Permission;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.RoleRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @author FHN
 * @date 2019/4/5 18:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleRepo roleRepo;

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }

    @Test
    @Transactional
    @Rollback(false)
    public void saveOrUpdate() throws Exception {

        Role role1 = new Role();
        role1.setRoleName("游客");
        role1.setRoleDesc("默认游客角色");
        System.out.println("role1 = " + roleService.saveOrUpdate(role1));
        Role role2 = new Role();
        role2.setRoleName("用户");
        role2.setRoleDesc("注册后为用户角色");
        System.out.println("role2 = " + roleService.saveOrUpdate(role2));
        Role role3 = new Role();
        role3.setRoleName("超级管理员");
        role3.setRoleDesc("超级管理员角色");
        System.out.println("role3 = " + roleService.saveOrUpdate(role3));
    }



    @Test
    @Transactional
    @Rollback(false)
    public void findById() throws Exception {
        Role role = roleService.findById(1L);
        System.out.println("role = " + role);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updatePermission() throws Exception {
        Role role = roleService.findById(3L);

        Permission permission1 = permissionService.findById(1L);
        Permission permission2 = permissionService.findById(2L);

        if (permission1 != null && permission2 != null) {
            // todo
        }
        Set<Permission> permissionSet = new HashSet<>();
        permissionSet.add(permission1);
        permissionSet.add(permission2);

        role.setPermissions(permissionSet);
        role.setPermissions(permissionSet);
        System.out.println("role = " + roleService.saveOrUpdate(role));
    }
    @Test
    @Transactional
    @Rollback(false)
    public void updateUser() throws Exception {
        Role role = roleService.findById(1L);

        User user = userService.findById(1L);
        User user1 = userService.findById(2L);


        if (user != null && user1 != null) {
            // todo
        }
        Set<User> userSet = role.getUsers();
        userSet.add(user);
        userSet.add(user1);

        role.setUsers(userSet);
        System.out.println("role = " + roleService.saveOrUpdate(role));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteById() throws Exception {
        roleService.deleteById(1L);
    }

    @Test
    public void findRolesByKey() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(false)
    public void all() throws Exception {
        saveOrUpdate();

    }

}