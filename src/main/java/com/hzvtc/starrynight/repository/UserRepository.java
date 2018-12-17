package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 廖师兄
 * 2016-11-03 23:17
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    //通过年龄来查询
    public List<UserEntity> findByAge(Integer age);
}
