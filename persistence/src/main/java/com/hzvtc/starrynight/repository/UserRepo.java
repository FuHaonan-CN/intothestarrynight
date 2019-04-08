package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description: 用户user数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findById(long id);

    /** 逻辑删除 */
    @Modifying
    @Query("update User u set u.isDel = '1' where u.id = ?1")
    int deleteById2(long id);

    void deleteById(long id);

    User findByEmail(String email);

    User findByUserName(String userName);

    User findByPhoneNum(String phone);

    User findByPhoneNumOrUserName(String phoneNum, String userName);

    Page<User> findAllByUserNameLike(String key,Pageable pageRequest);

    //通过年龄来查询
    //public List<User> findByAge(Integer age);
}
