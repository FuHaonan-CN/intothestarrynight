package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.entity.TUserInfo;
import com.hzvtc.starrynight.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hzvtc.starrynight.service.UserService;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 新增一个用户
     * @Param: TUserInfo
     */
    @Override
    public void save(TUserInfo user){
        userRepo.save(user);
    }

    /**
     * 根据name删除一个用户高
     * @Param: name
     */
    @Override
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }

}