package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.Const;
import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.UserRepo;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.service.PostService;
import com.hzvtc.starrynight.service.UserService;
import com.hzvtc.starrynight.response.Response;
import com.hzvtc.starrynight.response.ResponseData;
import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description: 首页controller
 * @Author: fhn
 * @Date: 2018/12/13 15:53
 */
//@RestController
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    //@Resource默认按 byName自动注入
    private final UserService userService;
    private final PostService postService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    public IndexController(UserService userService,
                           PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @LoggerManage(description = "首页")
    public String index(Model model) {
//		IndexCollectorView indexCollectorView = collectorService.getCollectors();
        //model.addAttribute("collector","");
        List sixNews = postService.findSixNews();
        model.addAttribute("news", sixNews);
        User user = super.getUser();
        if (null != user) {
            user.setId(11L);
            model.addAttribute("user", user);
        }
        return "homepage/index.html";
    }

    @RequestMapping(value = "/index/{name}", method = RequestMethod.GET)
    @LoggerManage(description = "登录页面")
    public String login(@PathVariable("name") String name) {
        return "homepage/" + name + ".html";
    }



    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }


//    public List<Girl> girlList() {
//        logger.info("girlList");
//
//         return "redirect:/index";
//    }
}
