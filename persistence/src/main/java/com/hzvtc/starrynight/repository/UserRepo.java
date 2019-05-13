package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.User;
import org.hibernate.annotations.Where;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @Description: 用户user数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
//    User findById(long  id);

    /** 逻辑删除 */
    @Modifying
    @Query("update User u set u.isDel = '1' where u.id = ?1")
    int deleteByIdFalse(long id);

    void deleteById(long id);

    User findByEmail(String email);

    User findByUserName(String userName);

    User findByPhoneNum(String phone);

    User findByPhoneNumOrUserName(String phoneNum, String userName);

    Page<User> findAllByUserNameLike(String key,Pageable pageRequest);

    //通过年龄来查询
    /**
     * TODO
     *
     * @param roleId .
     * @return java.util.Set<com.hzvtc.starrynight.entity.User>
     */
    @Query(value = "SELECT distinct * FROM user WHERE id IN (SELECT user_id FROM user_role WHERE role_id = ?1)", nativeQuery = true)
    Set<User> findUsersByRId(Long roleId);

    //public List<User> findByAge(Integer age);
}
