package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.Const;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.entity.result.ExceptionMsg;
import com.hzvtc.starrynight.entity.result.Response;
import com.hzvtc.starrynight.utils.Des3EncryptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    protected Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    protected Response result(ExceptionMsg msg){
    	return new Response(msg);
    }
    protected Response result(){
    	return new Response();
    }
    
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    protected HttpSession getSession() {
        return getRequest().getSession();
    }
    
    protected User getUser() {
        return (User) getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }
    
    protected long getUserId() {
    	Long id=0L;
    	User user=getUser();
    	if(user!=null){
    		id=user.getId();
    	}
        return id;
    }
    
    protected String getUserName() {
    	String userName="云收藏";
    	User user=getUser();
    	if(user!=null){
    		userName=user.getUserName();
    	}
        return userName;
    }
    
    protected String getUserIp() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }
    
    protected String getPwd(User user){
    	try {
            //密码原值
            Object credentials = user.getUserPassWord();
            //生成随机部分盐（部分，需要存入数据库中）
            String random = new SecureRandomNumberGenerator().nextBytes().toHex();
            user.setSalt(random);
            //真正的盐
            ByteSource salt = ByteSource.Util.bytes(user.getCredentialsSalt());
            return new Sha256Hash(credentials, salt).toHex();

    	    /*//加密方式
    	    String hashAlgorithmName = "MD5";
            //密码原值
            Object crdentials = user.getUserPassWord();
            //生成随机部分盐（部分，需要存入数据库中）
            String random = new SecureRandomNumberGenerator().nextBytes().toHex();
            user.setSalt(random);
            //真正的盐
            ByteSource salt = ByteSource.Util.bytes(user.getCredentialsSalt());
            //加密2次
            int hashIterations = 2;

            SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

            return hash.toString();*/

            /*最基础的加密*/
    		//String pwd = MD5Util.encrypt(password+Const.PASSWORD_KEY);
    		//return pwd;
		} catch (Exception e) {
			logger.error("密码加密异常：",e);
		}
    	return null;
    }

    protected String cookieSign(String value){
        try{
            value = value + Const.PASSWORD_KEY;
            String sign = Des3EncryptionUtil.encode(Const.DES3_KEY,value);
            return sign;
        }catch (Exception e){
            logger.error("cookie签名异常：",e);
        }
        return null;
    }

}
