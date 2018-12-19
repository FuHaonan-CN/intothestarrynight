package com.hzvtc.starrynight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Title: PostCollect
 * @Package: com.hzvtc.starrynight.entity
 * @Description: 收藏帖子表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
public class PostCollect extends BaseEntity {

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
