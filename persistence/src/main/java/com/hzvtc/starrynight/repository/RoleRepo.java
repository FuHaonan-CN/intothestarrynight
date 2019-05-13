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
//    Role findById(long id);

    /**
     * 逻辑删除
     * @param id .
     * @return  int
     */
    @Modifying
    @Query("update Role r set r.isDel = '1' where r.id = ?1")
    int deleteByIdFalse(long id);

    /**
     * 真实删除
     * @param id .
     */
    void deleteById(long id);

    /**
     * 根据name查找
     * @param name .
     */
    Role findByRoleName(String name);

    /**
     * 关键字查询列表包括分页
     *
     * @param key .
     * @param pageRequest .
     * @return org.springframework.data.domain.Page<com.hzvtc.starrynight.entity.Role>
     */
    Page<Role> findAllByRoleNameLike(String key,Pageable pageRequest);

}
