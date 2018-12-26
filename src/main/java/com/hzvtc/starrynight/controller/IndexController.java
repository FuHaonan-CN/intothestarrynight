package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 首页controller
 * @Author: fhn
 * @Date: 2018/12/13 15:53
 */
//@RestController
@Controller
@RequestMapping("/")
public class IndexController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    //@Resource默认按 byName自动注入
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/index",method= RequestMethod.GET)
    @LoggerManage(description="首页")
    public String index(Model model){
//		IndexCollectorView indexCollectorView = collectorService.getCollectors();
        model.addAttribute("collector","");
        User user = super.getUser();
        if(null != user){
            model.addAttribute("user",user);
        }
        return "index";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @PostMapping("/login")
    public String login(){
        return "index";
    }

//    public List<Girl> girlList() {
//        logger.info("girlList");
//
//         return "redirect:/index";
//    }
}
