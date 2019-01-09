package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 用户user数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findById(long  id);

    User findByUserNameOrEmail(String userName, String email);

    User findByEmail(String email);

    User findByUserName(String userName);

    User findByPhone(String phone);
    //通过年龄来查询
    //public List<User> findByAge(Integer age);
}
