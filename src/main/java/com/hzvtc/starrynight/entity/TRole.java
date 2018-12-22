package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 角色表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class TRole extends BaseEntity {

    /** 父级角色id */
    @Column(nullable = false)
    private Long parentRoleId;

    /** 角色名称 */
    @Column(nullable = false)
    private Long roleName;

    /** 角色描述 */
    private String roleDesc;

}
