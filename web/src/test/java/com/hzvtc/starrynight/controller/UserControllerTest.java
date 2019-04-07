package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
/**
 * UserControllerTest
 *
 * @author FHN
 * @date 2019/4/5 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserService userService;

    @Test
    public void girlList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("abc"));
    }


    @Test
    public void findOneTest() {
        User user = new User();
        //校验
//        Assert.assertEquals(new Integer(13), user.getIsDel());
    }

}