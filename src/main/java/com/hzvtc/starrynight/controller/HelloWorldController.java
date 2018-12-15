package com.hzvtc.starrynight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: HelloWorldController
 * @Package: com.hzvtc.starrynight.controller
 * @Description: TODO
 * @Author: fhn
 * @Date: 2018/12/13 15:53
 */
@RestController
public class HelloWorldController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World";
    }
}
