package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.Permission;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.repository.PermissionRepo;
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

import static org.junit.Assert.*;

/**
 * TODO .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/4/17 22:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionRepo permissionRepo;

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

        Permission permission1 = new Permission();
        permission1.setParentPermId(0L);
        permission1.setPermName("用户管理");
        permission1.setPermDesc("用户管理模块权限");

        Permission permission2 = new Permission();
        permission2.setParentPermId(0L);
        permission2.setPermName("角色管理");
        permission2.setPermDesc("角色管理模块权限");

        permissionService.saveOrUpdate(permission1);
        permissionService.saveOrUpdate(permission2);

//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(role1);
//        roleSet.add(role2);
//
//
//        role1.setPermissions(permissionSet);
//        permission1.setRoles(roleSet);
//
//        permissionService.saveOrUpdate(permission1);
//        roleService.saveOrUpdate(role1);

    }


    @Test
    @Transactional
    @Rollback(false)
    public void findById() throws Exception {
        Permission permission = permissionService.findById(2L);
        System.out.println("permission = " + permission);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void update() throws Exception {
        Permission permission = permissionService.findById(2L);
        System.out.println("permission = " + permission);
        Role role1 = roleService.findById(1L);
        Role role2 = roleService.findById(2L);

        Set<Role> roleSet = new HashSet<Role>();
        roleSet.add(role1);
        roleSet.add(role2);
        permission.setRoles(roleSet);

        Permission permission1 = permissionService.saveOrUpdate(permission);
        System.out.println("permission1 = " + permission1);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void deleteById() throws Exception {
        permissionService.deleteById(2L);
    }

}