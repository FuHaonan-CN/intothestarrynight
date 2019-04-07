package com.hzvtc.starrynight.comm.config;

import com.hzvtc.starrynight.comm.filter.AnyRolesAuthorizationFilter;
import com.hzvtc.starrynight.comm.filter.JwtAuthFilter;
import com.hzvtc.starrynight.service.UserService;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Map;

/**
 * @Title: ShiroConfig
 * @Description: TODO
 * @Author: fhn
 * @Date: 2019/1/31 20:00
 * @Version: 1.0
 */
@Configuration
public class ShiroConfig {
    /**
     * 注册shiro的Filter，拦截请求
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager,UserService userService) throws Exception{
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
        filterRegistration.setFilter((Filter)shiroFilter(securityManager, userService).getObject());
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ASYNC);

        return filterRegistration;
    }

    /**
     * 初始化Authenticator
     */
    @Bean
    public Authenticator authenticator(UserService userService) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
        authenticator.setRealms(Arrays.asList(jwtShiroRealm(userService), dbShiroRealm(userService)));
        //设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
     * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    /**
     * 用于用户名密码登录时认证的realm
     *
     * @param userService .
     * @return Realm .
     */
    @Bean("dbRealm")
    public Realm dbShiroRealm(UserService userService) {
        return new DbShiroRealm(userService);
    }

    /**
     * 用于JWT token认证的realm
     *
     * @param userService .
     * @return Realm .
     */
    @Bean("jwtRealm")
    public Realm jwtShiroRealm(UserService userService) {
        return new JWTShiroRealm(userService);
    }

    /**
     * 设置过滤器，将自定义的Filter加入
     *
     * @param securityManager .
     * @param userService .
     * @return ShiroFilterFactoryBean .
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, UserService userService) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        //外部自定义filter拦截器
        Map<String, Filter> filterMap = factoryBean.getFilters();
        filterMap.put("authcToken", createAuthFilter(userService));
        filterMap.put("anyRole", createRolesFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(securityManager);
        //登陆页面
        factoryBean.setLoginUrl("/index/login");
        //登陆成功页面
        factoryBean.setSuccessUrl("/index");
        //添加filterMap
        factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return factoryBean;
    }

    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/index/**", "anon");
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/fonts/**", "anon");
        chainDefinition.addPathDefinition("/img/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
        chainDefinition.addPathDefinition("/res/**", "anon");
        chainDefinition.addPathDefinition("/starry/**", "anon");
        chainDefinition.addPathDefinition("/role/**", "anon");
//        chainDefinition.addPathDefinition("/resources/**", "anon");

        chainDefinition.addPathDefinition("/login", "noSessionCreation,anon");
        chainDefinition.addPathDefinition("/register", "noSessionCreation,anon");
        //login不做认证，noSessionCreation的作用是用户在操作session时会抛异常
        //做用户认证，permissive参数的作用是当token无效时也允许请求访问，不会返回鉴权未通过的错误
        chainDefinition.addPathDefinition("/logout", "noSessionCreation,authcToken[permissive]");
        //允许已登录的用户访问
        chainDefinition.addPathDefinition("/user/**", "noSessionCreation,authcToken");
        //只允许admin或manager角色的用户访问
        chainDefinition.addPathDefinition("/admin/**", "anon");
//        chainDefinition.addPathDefinition("/admin/**", "noSessionCreation,authc,anyRole[admin,manager]");
//        chainDefinition.addPathDefinition("/article/list", "noSessionCreation,authcToken");
//        chainDefinition.addPathDefinition("/article/*", "noSessionCreation,authcToken[permissive]");
        // 默认进行用户鉴权
        chainDefinition.addPathDefinition("/**", "noSessionCreation,authc");
//        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    //注意不要加@Bean注解，不然spring会自动注册成filter
    protected JwtAuthFilter createAuthFilter(UserService userService){
        return new JwtAuthFilter(userService);
    }
    //注意不要加@Bean注解，不然spring会自动注册成filter
    protected AnyRolesAuthorizationFilter createRolesFilter(){
        return new AnyRolesAuthorizationFilter();
    }


}
