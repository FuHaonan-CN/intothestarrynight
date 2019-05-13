package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.Permission;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.UserRepo;
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
 * TODO .
 *
 * @author FHN
 * @date 2019/4/8 21:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

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
    public void saveOrEdit() throws Exception {
        /* add */
        User user = new User();
        user.setUserName("admin1");
        user.setUserPassWord("admin1");
        System.out.println("User = " + userService.save(user));

        User user2 = new User();
        user2.setUserName("admin2");
        user2.setUserPassWord("admin2");
        System.out.println("User2 = " + userService.save(user2));

    }


    @Test
    @Transactional
    @Rollback(false)
    public void update() throws Exception {

        User user = userService.findById(2L);

        Role role = roleService.findById(1L);
        Role role2 = roleService.findById(2L);


        if (role != null && role2 != null) {
            // todo
        }
        Set<Role> roleSet = user.getRoles();
        roleSet.add(role);
        roleSet.add(role2);

        user.setRoles(roleSet);
        System.out.println("user = " + userService.save(user));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void fingById() throws Exception {
        User user = userService.findById(2L);
        System.out.println("user = " + user.toString());

//        User save = userRepo.findById(123);
//        System.out.println("id = " + save.getId());
//        System.out.println("IsDel = " + save.getIsDel());
//        System.out.println("save = " + save.toString());
    }

    @Test
    @Transactional
    public void deleteById() throws Exception {
        /* 逻辑删除 */
//        User user = userRepo.findById(1);
//        user.setIsDel(1);
//        User save = userRepo.save(user);

//        System.out.println("id = " + save.getId());
//        System.out.println("save = " + save.toString());

        int b = userRepo.deleteByIdFalse(1);
        System.out.println("b = " + b);


        /* 物理删除 */
        // userRepo.deleteById(11);
    }

}