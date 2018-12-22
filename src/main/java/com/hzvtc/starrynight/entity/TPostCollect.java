package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Description: 收藏帖子表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class TPostCollect extends BaseEntity {

    /** 帖子id */
    @Column(nullable = false)
    private Long postId;

    /** 用户名 */
    @Column(nullable = false)
    private String userName;

    /** 收藏状态 */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int collectState = 0;

}
