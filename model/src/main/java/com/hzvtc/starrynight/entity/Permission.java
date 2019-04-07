package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: 权限表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class Permission extends BaseEntity {

    /** 父级权限id */
    @Column(nullable = false)
    private Long parentPermId;

    /** 权限名称 */
    @Column(nullable = false)
    private String permName;

    /** 权限描述 */
    private String permDesc;

    @ManyToMany
    @JoinTable(name="RolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<Role> roles;
}
