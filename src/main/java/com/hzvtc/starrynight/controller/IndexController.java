package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.Const;
import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.entity.result.ExceptionMsg;
import com.hzvtc.starrynight.entity.result.Response;
import com.hzvtc.starrynight.entity.result.ResponseData;
import com.hzvtc.starrynight.repository.UserRepo;
import com.hzvtc.starrynight.service.PostService;
import com.hzvtc.starrynight.service.UserService;
import com.hzvtc.starrynight.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.ZonedDateTime;
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
    public IndexController(UserService userService, PostService postService) {
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

    @RequestMapping(value = "/index/login", method = RequestMethod.GET)
    @LoggerManage(description = "登录页面")
    public String login(Model model) {
        return "homepage/login.html";
    }

    @RequestMapping(value = "/index/register", method = RequestMethod.GET)
    @LoggerManage(description = "登录页面")
    public String register(Model model) {
        return "homepage/register.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description = "登陆")
    @ResponseBody
    public ResponseData login(User user, HttpServletResponse response) {
        try {
            //这里不是bug，前端userName有可能是邮箱也有可能是昵称。
            User loginUser = userRepo.findByPhoneNumOrUserName(user.getPhoneNum(), user.getUserName());
            if (loginUser == null) {
                return new ResponseData(ExceptionMsg.LoginNameNotExists);
            } else if (!loginUser.getUserPassWord().equals(getPwd(user.getUserPassWord()))) {
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            }
            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, cookieSign(loginUser.getId().toString()));
            cookie.setMaxAge(Const.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
            String preUrl = "/";
            /*if (null != getSession().getAttribute(Const.LAST_REFERER)) {
                preUrl = String.valueOf(getSession().getAttribute(Const.LAST_REFERER));
                if (!preUrl.contains("/collect?") && !preUrl.contains("/lookAround/standard/")
                        && !preUrl.contains("/lookAround/simple/")) {
                    preUrl = "/";
                }
            }
            if (preUrl.contains("/lookAround/standard/")) {
                preUrl = "/lookAround/standard/ALL";
            }
            if (preUrl.contains("/lookAround/simple/")) {
                preUrl = "/lookAround/simple/ALL";
            }*/
            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("login failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @LoggerManage(description = "用户注册")
    @ResponseBody
    public Response create(User user, String phoneCaptcha) {
        try {
            User registerUser = userRepo.findByPhoneNum(user.getPhoneNum());
            if (null != registerUser) {
                return result(ExceptionMsg.PhoneUsed);
            }
            User userNameUser = userRepo.findByUserName(user.getUserName());
            if (null != userNameUser) {
                return result(ExceptionMsg.UserNameUsed);
            }
            ZonedDateTime zonedDateTime = DateUtils.getCurrentZonedDateTime();
            user.setUserPassWord(getPwd(user.getUserPassWord()));
            user.setAreaCode(user.getAreaCode());
            user.setCreateDate(zonedDateTime);
            user.setModifyDate(zonedDateTime);
            user.setRoleId(0L);
            userRepo.save(user);
            // 添加默认收藏夹
            //Favorites favorites = favoritesService.saveFavorites(user.getId(), "未读列表");
            // 添加默认属性设置
            //configService.saveConfig(user.getId(),String.valueOf(favorites.getId()));
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
        } catch (Exception e) {
            logger.error("create user failed, ", e);
            return result(ExceptionMsg.FAILED);
        }
        Response response = result();
        return response;
    }

//    public List<Girl> girlList() {
//        logger.info("girlList");
//
//         return "redirect:/index";
//    }
}
