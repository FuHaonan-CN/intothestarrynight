package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.service.RoleService;
import com.hzvtc.starrynight.service.UserService;
import org.junit.After;
import org.junit.Before;
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
 * TODO
 *
 * @author FHN
 * @date 2019/4/5 14:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserService userService;
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
    public void addContent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/main/role/addOrEdit.html"))
                //加参数
//                .param("page","0").param("size","2").param("key","")
                //表单请求类型
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andDo(MockMvcResultHandlers.print());
//                .andExpect(MockMvcResultMatchers.content().string("abc"));
    }

    @Test
    public void roleManager() throws Exception {
        Role role = new Role();
        role.setRoleName("ceshi111");
        role.setRoleDesc("ceshi111");
//        System.out.println(roleService.saveOrUpdate(role));

    }

}