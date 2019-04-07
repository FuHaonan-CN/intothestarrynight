package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void save(){
        User user = new User();
        user.setUserName("fhn1");
        user.setUserPassWord("asdfasdf");
        userRepo.save(user);
    }

}