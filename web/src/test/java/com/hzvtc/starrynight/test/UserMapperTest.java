package com.hzvtc.starrynight.test;

import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.UserRepo;
import com.hzvtc.starrynight.utils.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * UserMapperTest
 *
 * @author FHN
 * @date 2019/4/5 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SpringUtil springUtil;
    @Test
    public void testInsert() throws Exception {
        /*ApplicationContext applicationContext = springUtil.getApplicationContext();
        for (int i = 0; i < 10; i++) {
            User user = applicationContext.getBean(User.class);
            System.out.println("user = " + user);
        }*/


        /*User User = new User();
        //UUID uuid = UUID.randomUUID();
        //User.setXy0100(uuid.toString());
        User.setUserName("aa");
        User.setUserPassWord("bb");
        User.setActualName("fhn");
        System.out.println("User = " + User);
        userRepo.save(User);

        //Assert.assertEquals(3, userMapper.getAll().size());*/
    }

    @Test
    public void testEdit() throws Exception {
        User User = new User();
        //UUID uuid = UUID.randomUUID();
        //User.setXy0100(uuid.toString());
        User.setUserName("aa");
        User.setUserPassWord("bb");
        User.setActualName("fhn");
        System.out.println("User = " + User);
        userRepo.save(User);

        //Assert.assertEquals(3, userMapper.getAll().size());
    }

}