package com.hzvtc.starrynight.test;

import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Title: HelloWorldControllerTest
 * @Package: com.hzvtc.starrynight.controller
 * @Description: TODO
 * @Author: fhn
 * @Date: 2018/12/13 16:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserRepo userRepo;
    @Test
    public void testInsert() throws Exception {
        User User = new User();
        //UUID uuid = UUID.randomUUID();
        //User.setXy0100(uuid.toString());
        User.setUserName("aa");
        User.setUserPassWord("bb");
        User.setActualName("fhn");
        userRepo.save(User);

        //Assert.assertEquals(3, userMapper.getAll().size());
    }

}