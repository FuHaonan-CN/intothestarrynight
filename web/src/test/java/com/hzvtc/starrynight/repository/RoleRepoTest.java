package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * TODO .
 *
 * @author FHN
 * @date 2019/4/6 16:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepoTest {

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
    public void findById(){
//        Role role = new Role();
//        role.setId(6L);
        Role role = roleRepo.getOne(6L);
//        Optional<Role> role = roleRepo.findById(6L);
        System.out.println("role = " + role);

    }
    @Test
    public void findById1(){
//        Role role = new Role();
//        role.setId(6L);

//        Role role = roleRepo.findById(6L);

        Optional<Role> role1 = roleRepo.findById(6L);
        Role role = role1.orElse(null);

        //        System.out.println("role = " + role.getUsers());

//        ToStringBuilder.reflectionToString(role);

    }

}