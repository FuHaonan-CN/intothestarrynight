package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.utils.ResultUtil;
import com.hzvtc.starrynight.entity.result.Result;
import com.hzvtc.starrynight.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoleController .
 *
 * @author FHN
 * @date 2019/4/6 17:24
 */
@Controller
@RequestMapping("/role")
@RestController
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @LoggerManage(description = "role管理方法")
    @ResponseBody
    public Result findOne(Long id) {
        Role role = roleService.findById(id);
//        return role;
        return ResultUtil.success(role);
    }
}
