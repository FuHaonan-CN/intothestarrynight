package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.TUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: user数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface UserRepository extends JpaRepository<TUserInfo, Long> {

    //通过年龄来查询
    //public List<TUserInfo> findByAge(Integer age);
}
