package com.hzvtc.starrynight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Title: Permission
 * @Package: com.hzvtc.starrynight.entity
 * @Description: 权限表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
public class Permission extends BaseEntity {

    /** 权限名称 */
    @Column(nullable = false)
    private Long permName;

}
