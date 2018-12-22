package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.TPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: Permission数据库CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface PermissionRepository extends JpaRepository<TPermission, Long> {

}
