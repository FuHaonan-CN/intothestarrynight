package com.hzvtc.starrynight.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Title: Role
 * @Package: com.hzvtc.starrynight.entity
 * @Description: 角色表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
public class Role extends BaseEntity {

    /** 角色名称 */
    @Column(nullable = false)
    private Long roleName;

    /** 权限id */
    @Column(nullable = false)
    private Long permId;
}
