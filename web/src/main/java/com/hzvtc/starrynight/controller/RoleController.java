package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.error.UserException;
import com.hzvtc.starrynight.response.ResRole;
import com.hzvtc.starrynight.utils.ResultUtil;
import com.hzvtc.starrynight.response.Result;
import com.hzvtc.starrynight.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * RoleController .
 *
 * @author FHN
 * @date 2019/4/6 17:24
 */
//@Controller
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController extends BaseController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @LoggerManage(description = "新增角色")
    @ResponseBody
    public Result save(Role role) {

        Role reqRole = roleService.findByRoleName(role.getRoleName());
        if (null != reqRole) {
            return ResultUtil.error(EmExceptionMsg.RoleNameUsed);
        }
        roleService.saveOrUpdate(role);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    @LoggerManage(description = "role管理方法")
    @ResponseBody
    public Result findOne(Long id) {

        Role role = roleService.findById(id);
        return ResultUtil.success(role);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @LoggerManage(description = "role管理方法")
    @ResponseBody
    public Result findById(Long id) throws UserException {
        Role role = roleService.findById(id);
        if (isEmpty(role)){
            throw new UserException(EmExceptionMsg.NULLRESULT, "无法查询到该角色信息，请核对！");
        }
        ResRole resRole = new ResRole();
        BeanUtils.copyProperties(role, resRole);
        return ResultUtil.success(resRole);

    }

    @RequestMapping(value = "/queryAllRole", method = RequestMethod.POST)
    @LoggerManage(description = "数据获取")
    @ResponseBody
    public Result queryAllRole(int PageIndex, int PageSize, String key) {
        PageIndex -= 1;
        Page data = roleService.findRolesByKey(PageIndex, PageSize, key);
        return ResultUtil.success(data);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @LoggerManage(description = "删除")
    public Result delete(String ids) {
        try {
            String[] id_ = ids.split(",");
            for (String id:id_) {
                roleService.deleteById(Long.valueOf(id));
            }
            return ResultUtil.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(EmExceptionMsg.DELETE_ERR);
        }

    }
}
