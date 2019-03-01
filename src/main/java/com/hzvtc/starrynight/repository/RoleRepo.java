package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description: 角色role数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface RoleRepo extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Page<Role> findAllByRoleNameLike(String key,Pageable pageRequest);

}
