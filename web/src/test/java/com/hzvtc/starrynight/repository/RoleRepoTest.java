package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void findById(){
//        Role role = new Role();
//        role.setId(6L);
        Role role = roleRepo.getOne(6L);
//        Optional<Role> role = roleRepo.findById(6L);
        System.out.println("role = " + role);

    }

}