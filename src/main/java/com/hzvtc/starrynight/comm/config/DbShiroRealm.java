package com.hzvtc.starrynight.comm.config;

import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;


public class DbShiroRealm extends AuthorizingRealm {
    private final Logger log = LoggerFactory.getLogger(DbShiroRealm.class);

    @Resource
    private UserService userService;

    public DbShiroRealm(UserService userService) {
        this.userService = userService;
        this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }
    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userpasswordToken = (UsernamePasswordToken) token;
        String username = userpasswordToken.getUsername();
        User user = userService.findByPhoneNumOrUserName(username, username);
        System.out.println("----->>userInfo=" + user);
        if (user == null) {
            throw new AuthenticationException("用户名不存在");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getUserPassWord(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
//		return new SimpleAuthenticationInfo(user, user.getUserPassWord(), ByteSource.Util.bytes(user.getCredentialsSalt()), "dbRealm");
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->DbShiroRealm.doGetAuthorizationInfo()");
        String username = JwtUtils.getUsername(principals.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.findByPhoneNumOrUserName(username, username);
        addRole(user,simpleAuthorizationInfo);
        return simpleAuthorizationInfo;
    }
    /**
     * 添加角色
     * @param user
     * @param simpleAuthorizationInfo
     */
    private void addRole(User user, SimpleAuthorizationInfo simpleAuthorizationInfo) {
        List<Role> roles = user.getRoleList();
        if(roles!=null&&roles.size()>0){
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getRoleName());
            }
        }
    }

    /**
     * 添加权限
     * @param username
     * @param info
     * @return
     */
//    private SimpleAuthorizationInfo addPermission(String username,SimpleAuthorizationInfo info) {
//        List<Permission> permissions = permissionDao.findPermissionByName(username);
//        for (Permission permission : permissions) {
//            info.addStringPermission(permission.getUrl());//添加权限
//        }
//        return info;
//    }

}
