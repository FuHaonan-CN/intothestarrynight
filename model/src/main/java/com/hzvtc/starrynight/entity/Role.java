package com.hzvtc.starrynight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 角色表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
//@JsonIgnoreProperties(value = { "users" })
public class Role extends BaseEntity {
    public Role() {
    }

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
//    @JoinTable(name="RolePermission",
//            joinColumns={@JoinColumn(name="roleId", nullable = false, updatable = false)},
//            inverseJoinColumns={@JoinColumn(name="permissionId", nullable = false, updatable = false)})
    private Set<Permission> permissions = new HashSet<>();

    /** 用户 - 角色关系：多对多关系 */
//    @JsonIgnoreProperties(value = {"roles","users"})
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
//    @JsonIgnore
//    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
//    @JoinTable(name="UserRole",
//            joinColumns={@JoinColumn(name="roleId", nullable = false, updatable = false)},
//            inverseJoinColumns={@JoinColumn(name="uid", nullable = false, updatable = false)})
    private Set<User> users = new HashSet<>();

    /*操作人*/

    @Override
    public String toString() {
        return "Role{" +
                "id" + super.getId() +
                "createDate" + super.getCreateDate() +
                "modifyDate" + super.getModifyDate() +
                "roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}
