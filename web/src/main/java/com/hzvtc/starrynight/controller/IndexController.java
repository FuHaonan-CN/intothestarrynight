package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.Const;
import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.repository.UserRepo;
import com.hzvtc.starrynight.service.PostService;
import com.hzvtc.starrynight.service.UserService;
import com.hzvtc.starrynight.entity.result.ExceptionMsg;
import com.hzvtc.starrynight.entity.result.Response;
import com.hzvtc.starrynight.entity.result.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoggerManage(description = "登录")
    @ResponseBody
    public ResponseData login(User loginInfo, HttpServletRequest request, HttpServletResponse response) throws Exception{
        logger.info("=================dologin==============");
        String msg ;
        Subject subject = SecurityUtils.getSubject();
        try {
            String userPhoneOrName = StringUtils.isBlank(loginInfo.getUserName()) ? loginInfo.getPhoneNum() : null;
            UsernamePasswordToken token = new UsernamePasswordToken(userPhoneOrName, loginInfo.getUserPassWord());

            subject.login(token);
            String preUrl = "/";
            User user = (User) subject.getPrincipal();
            String newToken = userService.generateJwtToken(user.getUserName());
            response.setHeader("x-auth-token", newToken);

            // TODO: 此处存入cookie和session

            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);
//            return ResponseEntity.ok().build();

        }catch (IncorrectCredentialsException e) {
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
        }catch (AuthenticationException e) {
            logger.error("User {} login fail, Reason:{}", loginInfo.getUserName(), e.getMessage());
            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseData(ExceptionMsg.FAILED);
//        catch (AuthenticationException e) {
//            logger.error("User {} login fail, Reason:{}", loginInfo.getUserName(), e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }

        //跨域
        /*response.setHeader("Access-Control-Allow-Origin","*");
        String msg ;
        Subject currentUser = SecurityUtils.getSubject();
        String userPhoneOrName = StringUtils.isBlank(user.getUserName()) ? user.getPhoneNum() : null;
        UsernamePasswordToken token = new UsernamePasswordToken(userPhoneOrName,user.getUserPassWord());
        token.setRememberMe(true);
        try {
            currentUser.login(token);
            String preUrl = "/";

            // TODO: 此处存入cookie和session

            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);
        }catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            System.out.println(msg);
        }
        return new ResponseData(ExceptionMsg.FAILED);*/

        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        /*String exception = (String) request.getAttribute("shiroLoginFailure");
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
        return "/login";*/

    }

    /*public ResponseData login(User user, HttpServletResponse response) {
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
            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("login failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }*/

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @LoggerManage(description = "用户注册")
    @ResponseBody
    public Response create(User user, String phoneCaptcha) {
        try {
            User phoneNumUser = userRepo.findByPhoneNum(user.getPhoneNum());
            if (null != phoneNumUser) {
                return result(ExceptionMsg.PhoneUsed);
            }
            User userNameUser = userRepo.findByUserName(user.getUserName());
            if (null != userNameUser) {
                return result(ExceptionMsg.UserNameUsed);
            }
//            ZonedDateTime zonedDateTime = DateUtils.getCurrentZonedDateTime();
            user.setUserPassWord(getPwd(user));
            user.setAreaCode(user.getAreaCode());
//            user.setCreateDate(zonedDateTime);
//            user.setModifyDate(zonedDateTime);
            //user.setRoleList();
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
        return result();
    }

//    public List<Girl> girlList() {
//        logger.info("girlList");
//
//         return "redirect:/index";
//    }
}
