package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Description: 帖子评论表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class TPostComment extends BaseEntity {

    /** 帖子id */
    @Column(nullable = false)
    private Long postId;

    /** 父id */
    @Column(nullable = false)
    private Long pid;

    /** 用户id */
    @Column(nullable = false)
    private Long userId;

    /** 用户名 */
    @Column(nullable = false)
    private String userName;

    /**  评论内容 */
    @Column(nullable = false)
    private String comment;

    /** 删除标记 */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int isDel = 0;
}
