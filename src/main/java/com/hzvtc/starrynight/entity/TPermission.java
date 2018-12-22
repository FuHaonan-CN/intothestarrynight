package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Description: 权限表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class TPermission extends BaseEntity {

    /** 父级权限id */
    @Column(nullable = false)
    private Long parentPermId;

    /** 权限名称 */
    @Column(nullable = false)
    private Long permName;

    /** 权限描述 */
    private Long permDesc;

}
