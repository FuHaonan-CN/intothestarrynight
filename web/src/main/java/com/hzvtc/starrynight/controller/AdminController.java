package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.service.PostService;
import com.hzvtc.starrynight.utils.ResultUtil;
import com.hzvtc.starrynight.entity.result.Result;
import com.hzvtc.starrynight.service.RoleService;
import com.hzvtc.starrynight.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public AdminController(UserService userService,
                           PostService postService,
                           RoleService roleService) {
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

    /**
     * 对admin页面所有main iframe的页面转发
     */
    @RequestMapping(value = "/main/{name}", method = RequestMethod.GET)
    @LoggerManage(description = "main内容")
    public String main(@PathVariable("name") String name) {
        return "admin/main/" + name + ".html";
    }
    /**
     * 对admin页面具体模块的增加修改页面转发
     */
    @RequestMapping(value = "/main/{name}/{content}.html", method = RequestMethod.GET)
    @LoggerManage(description = "具体模块的增加修改页面")
    public String addContent(@PathVariable("name") String name, @PathVariable("content") String content) {
        return "admin/" + name + "/" + content + ".html";
    }

    @RequestMapping(value = "/data/{name}", method = RequestMethod.POST)
    @LoggerManage(description = "各类信息数据获取")
    @ResponseBody
    public Result getData(@PathVariable("name") String name,int page, int size, String key) {
        Page data = null;
        switch (name){
            case "userPage":
                data = userService.findUsersByKey(page, size, key); break;
            case "rolePage":
                 data = roleService.findRolesByKey(page, size, key); break;
            default:
                System.out.println("暂时无法处理该请求！");
                break;
        }
        System.out.println("data = " + data.toString());
        return ResultUtil.success(data);
    }

    @RequestMapping(value = "/role/addOrEdit", method = RequestMethod.POST)
    @LoggerManage(description = "role管理方法")
    @ResponseBody
    public Result roleManager(Role role) {
        roleService.saveOrUpdate(role);
        return ResultUtil.success();
    }



    /**
     * 对admin页面所有main iframe的页面转发
     */
    /*@RequestMapping(value = "/{clazz}/{method}", method = RequestMethod.GET)
    @LoggerManage(description = "main页面切换")
    public String addRole(@PathVariable("clazz") String clazz, @PathVariable("method") String method) {
        if ("role".equals(clazz)) {

        }
        return "123123";
    }*/


    /*@RequestMapping(value = "/data/userPage", method = RequestMethod.POST)
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
    }*/


}
