package com.hzvtc.starrynight.test;

import com.hzvtc.starrynight.entity.Xy01;
import com.hzvtc.starrynight.repository.UserRepository;
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
    private UserRepository userRepository;
    @Test
    public void testInsert() throws Exception {
        Xy01 Xy01 = new Xy01();
        //UUID uuid = UUID.randomUUID();
        //Xy01.setXy0100(uuid.toString());
        Xy01.setXy0101("aa");
        Xy01.setXy0102("bb");
        Xy01.setXy0103("fhn");
        userRepository.save(Xy01);
        //userMapper.insert(new Xy01("bb", "b123456", "fhn1"));
        //userMapper.insert(new Xy01("cc", "b123456", "fhn2"));

        //Assert.assertEquals(3, userMapper.getAll().size());
    }

}