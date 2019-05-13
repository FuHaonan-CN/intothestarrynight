package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.Const;
import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.response.ResUser;
import com.hzvtc.starrynight.response.ResponseData;
import com.hzvtc.starrynight.response.Result;
import com.hzvtc.starrynight.service.UserService;
import com.hzvtc.starrynight.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * TODO .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/4/14 17:07
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @LoggerManage(description = "新增User方法")
    public Result save(Long id) {
        User user = userService.findById(id);
        return ResultUtil.success(user);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @LoggerManage(description = "用户注册")
    @ResponseBody
    public Result create(User user, String phoneCaptcha) {
        try {
            User phoneNumUser = userService.findByPhoneNum(user.getPhoneNum());
            if (null != phoneNumUser) {
                return ResultUtil.error(EmExceptionMsg.PhoneUsed);
            }
            User userNameUser = userService.findByUserName(user.getUserName());
            if (null != userNameUser) {
                return ResultUtil.error(EmExceptionMsg.UserNameUsed);
            }
//            ZonedDateTime zonedDateTime = DateUtils.getCurrentZonedDateTime();
            user.setUserPassWord(getPwd(user));
//            user.setAreaCode(user.getAreaCode());
//            user.setCreateDate(zonedDateTime);
//            user.setModifyDate(zonedDateTime);
            //user.setRoleList();
            userService.save(user);
            // 添加默认收藏夹
            //Favorites favorites = favoritesService.saveFavorites(user.getId(), "未读列表");
            // 添加默认属性设置
            //configService.saveConfig(user.getId(),String.valueOf(favorites.getId()));
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
        } catch (Exception e) {
//            logger.error("create user failed, ", e);
            return ResultUtil.error(EmExceptionMsg.UNKNOWN_ERROR);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description = "登录")
    @ResponseBody
    public Result login(User loginInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("=================dologin==============");
        String msg;
        Subject subject = SecurityUtils.getSubject();
        try {
            String userPhoneOrName = StringUtils.isBlank(loginInfo.getUserName()) ? loginInfo.getPhoneNum() : loginInfo.getUserName();
            UsernamePasswordToken token = new UsernamePasswordToken(userPhoneOrName, loginInfo.getUserPassWord());

            subject.login(token);
            String preUrl = "/";
            User user = (User) subject.getPrincipal();
            String newToken = userService.generateJwtToken(user.getUserName());
            response.setHeader("x-auth-token", newToken);

//            Cookie cookie = new Cookie("11111", "111111");
//            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(user.getId().toString()));
//            cookie.setMaxAge(Const.COOKIE_TIMEOUT);
//            cookie.setDomain("127.0.0.1");
//            cookie.setPath("/");
//            response.addCookie(cookie);
//            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            Map<String, String> map = new HashMap<>(16);
            map.put("preUrl", preUrl);
            map.put("token", newToken);
            return ResultUtil.success(map);

        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + loginInfo.getUserName() + " was incorrect.";
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + loginInfo.getUserName() + " was locked.";
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + loginInfo.getUserName() + " was disabled.";
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + loginInfo.getUserName() + "  was expired.";
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + loginInfo.getUserName();
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            System.out.println(msg);
        } catch (AuthenticationException e) {
            // 密码错误
            logger.error("User {} login fail, Reason:{}", loginInfo.getUserName(), e.getMessage());
            System.out.println(e.getMessage());
            return ResultUtil.error(EmExceptionMsg.PassWordError);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResultUtil.error(EmExceptionMsg.UNKNOWN_ERROR);
        }
        return ResultUtil.error(EmExceptionMsg.UNKNOWN_ERROR);

    }

    /*public ResponseData login(User user, HttpServletResponse response) {
        try {
            //这里不是bug，前端userName有可能是邮箱也有可能是昵称。
            User loginUser = userRepo.findByPhoneNumOrUserName(user.getPhoneNum(), user.getUserName());
            if (loginUser == null) {
                return new ResponseData(EmExceptionMsg.LoginNameNotExists);
            } else if (!loginUser.getUserPassWord().equals(getPwd(user.getUserPassWord()))) {
                return new ResponseData(EmExceptionMsg.LoginNameOrPassWordError);
            }
            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(loginUser.getId().toString()));
            cookie.setMaxAge(Const.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
            String preUrl = "/";
            return new ResponseData(EmExceptionMsg.SUCCESS, preUrl);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("login failed, ", e);
            return new ResponseData(EmExceptionMsg.FAILED);
        }
    }*/


    @RequestMapping(value = "/queryAllUsers", method = RequestMethod.POST)
    @LoggerManage(description = "数据获取")
    @ResponseBody
    public Result queryAllRole(int PageIndex, int PageSize, String key) {
        PageIndex -= 1;
        Page data = userService.findUsersByKey(PageIndex, PageSize, key);
        return ResultUtil.success(data);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @LoggerManage(description = "根据id来查找")
    public Result findById(Long id) {
        User user = userService.findById(id);
        ResUser resUser = new ResUser();
        BeanUtils.copyProperties(user, resUser);
        return ResultUtil.success(resUser);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    @LoggerManage(description = "根据用户名来查找")
    public User findByName(String name) {
        User user = userService.findByUserName(name);
        return user;
    }

    @GetMapping("/findUsersByRId")
    @LoggerManage(description = "根据角色来查找所有用户")
    public Result findUsersByRId(Long id) {
        Set<User> usersByRId = userService.findUsersByRId(id);
        return ResultUtil.success(usersByRId);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @LoggerManage(description = "删除")
    public Result delete(String ids) {
        try {
            String[] id_ = ids.split(",");
            for (String id:id_) {
                userService.deleteByIdFalse(Long.valueOf(id));
            }
            return ResultUtil.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(EmExceptionMsg.DELETE_ERR);
        }

    }
}
