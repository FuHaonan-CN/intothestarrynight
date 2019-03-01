package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.entity.result.Result;
import com.hzvtc.starrynight.service.PostService;
import com.hzvtc.starrynight.service.RoleService;
import com.hzvtc.starrynight.service.UserService;
import com.hzvtc.starrynight.utils.ResultUtil;
import org.apache.catalina.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: AdminController
 * @Author: fhn
 * @Date: 2018/12/13 15:53
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;
    private final PostService postService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, PostService postService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.postService = postService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @LoggerManage(description = "admin首页")
    public String index(Model model) {
//        List sixNews = postService.findSixNews();
//        model.addAttribute("news", sixNews);
//        User user = super.getUser();
//        if (null != user) {
//            user.setId(11L);
//            model.addAttribute("user", user);
//        }
        return "admin/index.html";
    }
    @RequestMapping(value = "/main_index", method = RequestMethod.GET)
    @LoggerManage(description = "main内容")
    public String main(Model model) {
        return "admin/main/main.html";
    }

    @RequestMapping(value = "/main_user", method = RequestMethod.GET)
    @LoggerManage(description = "用户管理页面")
    public String userManager(Model model) {
        return "admin/main/main_user.html";
    }

    @RequestMapping(value = "/main_role", method = RequestMethod.GET)
    @LoggerManage(description = "角色管理页面")
    public String roleManager(Model model) {
        return "admin/main/main_role.html";
    }

    @RequestMapping(value = "/permissionManager", method = RequestMethod.GET)
    @LoggerManage(description = "权限管理页面")
    public String permissionManager(Model model) {
        return "admin/main_permission.html";
    }

    @RequestMapping(value = "/allPostManager", method = RequestMethod.GET)
    @LoggerManage(description = "所有帖子管理页面")
    public String allPostManager(Model model) {
        return "admin/allPostManager.html";
    }
    @RequestMapping(value = "/myPostManager", method = RequestMethod.GET)
    @LoggerManage(description = "我的帖子管理页面")
    public String myPostManager(Model model) {
        return "admin/myPostManager.html";
    }
    @RequestMapping(value = "/homePostManager", method = RequestMethod.GET)
    @LoggerManage(description = "首页轮播新闻管理页面")
    public String homePostManager(Model model) {
        return "admin/homePostManager.html";
    }
    @RequestMapping(value = "/starInfoManager", method = RequestMethod.GET)
    @LoggerManage(description = "星球信息管理")
    public String staryInfoManager(Model model) {
        return "admin/starInfoManager.html";
    }



    @RequestMapping(value = "/data/userPage", method = RequestMethod.POST)
    @LoggerManage(description = "用户分页数据")
    @ResponseBody
    public Result userPageData(int page, int size, String key) {
        return ResultUtil.success(userService.findUsersByKey(page, size, key));
    }

    @RequestMapping(value = "/data/rolePage", method = RequestMethod.POST)
    @LoggerManage(description = "角色分页数据")
    @ResponseBody
    public Result rolePageData(int page, int size, String key) {
        return ResultUtil.success(roleService.findRolesByKey(page, size, key));
    }

    @RequestMapping(value = "/data/permissionPage", method = RequestMethod.POST)
    @LoggerManage(description = "权限分页数据")
    @ResponseBody
    public Result permissionPageData(int page, int size, String key) {
        return ResultUtil.success(userService.findUsersByKey(page, size, key));
    }

    @RequestMapping(value = "/data/allPostPage", method = RequestMethod.POST)
    @LoggerManage(description = "所有帖子分页数据")
    @ResponseBody
    public Result allPostPageData(int page, int size, String key) {
        return ResultUtil.success(userService.findUsersByKey(page, size, key));
    }
    @RequestMapping(value = "/data/myPostPage", method = RequestMethod.POST)
    @LoggerManage(description = "我的帖子分页数据")
    @ResponseBody
    public Result myPostPageData(int page, int size, String key) {
        return ResultUtil.success(userService.findUsersByKey(page, size, key));
    }
    @RequestMapping(value = "/data/homePostPage", method = RequestMethod.POST)
    @LoggerManage(description = "首页轮播新闻分页数据")
    @ResponseBody
    public Result homePostPageData(int page, int size, String key) {
        return ResultUtil.success(userService.findUsersByKey(page, size, key));
    }
    @RequestMapping(value = "/data/starInfoPage", method = RequestMethod.POST)
    @LoggerManage(description = "星球信息分页数据")
    @ResponseBody
    public Result starInfoPageData(int page, int size, String key) {
        return ResultUtil.success(userService.findUsersByKey(page, size, key));
    }

}
