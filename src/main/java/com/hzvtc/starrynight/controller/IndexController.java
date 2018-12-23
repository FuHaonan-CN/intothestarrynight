package com.hzvtc.starrynight.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.annotation.Resource;

/**
 * @Description: 首页controller
 * @Author: fhn
 * @Date: 2018/12/13 15:53
 */
//@RestController
@Controller
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    //@Resource默认按 byName自动注入
    final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public List<Girl> girlList() {
//        logger.info("girlList");
//
//        return girlRepository.findAll();
//    }
}
