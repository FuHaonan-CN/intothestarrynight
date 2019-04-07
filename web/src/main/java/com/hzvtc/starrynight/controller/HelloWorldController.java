package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.result.Result;
import com.hzvtc.starrynight.service.UserService;
import com.hzvtc.starrynight.utils.ResultUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Title: HelloWorldController
 * @Package: com.hzvtc.starrynight.controller
 * @Description: TODO
 * @Author: fhn
 * @Date: 2018/12/13 15:53
 */
@Controller
@RequestMapping("/test")
public class HelloWorldController extends BaseController {

    @Autowired
    private final UserService userService;

    public HelloWorldController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/","/index"})
    public String index(){
        return"/homepage/index";
    }

    @RequestMapping(value = "/index/login", method = RequestMethod.GET)
    @LoggerManage(description = "登录页面")
    public String login(Model model) {
        return "homepage/login.html";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @LoggerManage(description = "管理员页面")
    public String admin(Model model) {
        return "admin/admin.html";
    }

    @RequestMapping(value = "/admin/manager", method = RequestMethod.GET)
    @LoggerManage(description = "admin管理界面")
    public String adminManager(Model model) {
        return "admin/adminManager.html";
    }

    @RequestMapping(value = "/admin/page", method = RequestMethod.GET)
    @LoggerManage(description = "page页面")
    public String page(Model model) {
//        Result<User> result = ResultUtil.success(userService.findUsersByKey(page, size, key));
//        Page<User> datas = userService.findUsersByKey(page, size, key);
//        model.addAttribute("result", result);
        return "admin/adminManager.html";
//        return "admin/commonModule/page.html";

    }

    @RequestMapping(value = "/admin/pageData", method = RequestMethod.POST)
    @LoggerManage(description = "page数据")
    @ResponseBody
    public Result pagesj(int page,int size,String key) {

        return ResultUtil.success(userService.findUsersByKey(page, size, key));
    }
}


