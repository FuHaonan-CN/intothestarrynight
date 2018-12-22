package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.TRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: role数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface RoleRepository extends JpaRepository<TRole, Long> {

}
