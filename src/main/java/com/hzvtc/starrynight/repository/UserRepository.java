package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.Xy01;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 廖师兄
 * 2016-11-03 23:17
 */
public interface UserRepository extends JpaRepository<Xy01, Integer> {

    //通过年龄来查询
    //public List<Xy01> findByAge(Integer age);
}
