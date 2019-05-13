package com.hzvtc.starrynight.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    @ManyToMany(cascade = CascadeType.ALL)
    @ManyToMany(fetch= FetchType.LAZY, mappedBy = "permissions")
//    @JsonIgnore
//    @JoinTable(name="RolePermission",
//            joinColumns={@JoinColumn(name="permissionId", nullable = false, updatable = false)},
//            inverseJoinColumns={@JoinColumn(name="roleId", nullable = false, updatable = false)})
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Permission{" +
                "id" + super.getId() +
                "createDate" + super.getCreateDate() +
                "modifyDate" + super.getModifyDate() +
                "parentPermId=" + parentPermId +
                ", permName='" + permName + '\'' +
                ", permDesc='" + permDesc + '\'' +
                '}';
    }
}
