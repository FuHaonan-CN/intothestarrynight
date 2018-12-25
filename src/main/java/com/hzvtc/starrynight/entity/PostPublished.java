package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Description: 发布帖子表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class PostPublished extends BaseEntity {

    /** 发帖人id */
    @Column(nullable = false)
    private Long userId;

    /** 帖子标题 */
    @Column(nullable = false)
    private String postTitle;

    /** 内容 */
    @Column(nullable = false)
    private String postContent;

    /** 图片id */
    @Column(nullable = false)
    private Long picId;

    /** 点赞数 */
    @Column(nullable = false, columnDefinition="int default 0")
    private int likeNum = 0;

    /** 收藏数 */
    @Column(nullable = false, columnDefinition="int default 0")
    private int collectNum = 0;

    /** 审核人id */
    private Long reviewerId;

    /** 审核状态 */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int reviewState = 0;

    /** 删除标记 */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int isDel = 0;

}
