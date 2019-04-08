package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.User;
import org.springframework.data.domain.Page;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 新增一个用户
     * @param user .
     * @return User
     */
    public User save(User user);


    /**
     * 根据id删除一个用户
     * @Param: name
     */
    public void deleteById(Long id);

    public User loginCheck(User user);

    User findByUserName(String username);

    User findByPhoneNumOrUserName(String phonrNum, String userName);

    /**
     * 关键字查询包括分页
     * @param page： 当前页
     * @param pageSize：每页显示条数
     * @param key：模糊查询关键字
     * @return Page<User>: 用户列表
     */
    Page<User> findUsersByKey(int page, int pageSize, String key);

    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username
     * @return
     */
    User getJwtTokenInfo(String username);
    /**
     * 保存user登录信息，返回token
     * @param username
     */
    String generateJwtToken(String username);

}
