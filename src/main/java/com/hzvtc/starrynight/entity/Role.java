package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Description: 角色表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class Role extends BaseEntity {

    /** 角色名称 */
    @Column(nullable = false)
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 是否有效 */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int isDel = 0;

    /** 角色 - 权限关系：多对多关系 */
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="RolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<Permission> permissions;

    /** 用户 - 角色关系：多对多关系 */
    @ManyToMany
    @JoinTable(name="UserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<User> users;

}
