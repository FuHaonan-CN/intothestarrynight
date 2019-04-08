package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Description: 角色role数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findById(long id);

    /**
     * 逻辑删除
     *
     * @param id .
     * @return  int
     */
    @Modifying
    @Query("update Role u set u.isDel = '1' where u.id = ?1")
    int deleteById2(long id);

    void deleteById(long id);

    Page<Role> findAllByRoleNameLike(String key,Pageable pageRequest);

}
