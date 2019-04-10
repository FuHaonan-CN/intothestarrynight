package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO .
 *
 * @author FHN
 * @date 2019/4/6 16:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }

    @Test
    public void saveOrEdit() throws Exception {
        /* add */
        User user = new User();
        user.setUserName("admin2");
        user.setUserPassWord("admin2");
        User save = userRepo.save(user);
        System.out.println("id = " + save.getId());
        System.out.println("save = " + save.toString());

        /* edit */
//        User user = userRepo.findById(10);
//        user.setUserName("admin33");
//        user.setUserPassWord("admin33");
//        User save = userRepo.save(user);
//
//        System.out.println("id = " + save.getId());
//        System.out.println("save = " + save.toString());
    }

    @Test
    public void fingById() throws Exception {

        User save = userRepo.findById(1);

        System.out.println("id = " + save.getId());
        System.out.println("IsDel = " + save.getIsDel());
        System.out.println("save = " + save.toString());
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

        int b = userRepo.deleteById2(1);
        System.out.println("b = " + b);


        /* 物理删除 */
        // userRepo.deleteById(11);
    }


}