package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.User;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 新增一个用户
     * @Param: User
     */
    public void save(User user);

    /**
     * 根据id删除一个用户
     * @Param: name
     */
    public void deleteById(Long id);

    public User loginCheck(User user);

    User findByUserName(String username);

    User findByPhoneNumOrUserName(String phonrNum, String userName);
}
