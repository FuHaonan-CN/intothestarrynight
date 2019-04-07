package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.repository.RoleRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void saveOrUpdate() throws Exception {
//        Role role = new Role();
        Role role = roleService.findById(6L);
        role.setRoleName("ceshi222");
        role.setRoleDesc("ceshi222");
        System.out.println("role = " + role.toString());

        System.out.println(roleService.saveOrUpdate(role));

    }

    @Test
    public void findById() throws Exception {
        Role role = roleService.findById(6L);
        System.out.println("role = " + role);
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void findRolesByKey() throws Exception {
    }

}