package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Description: 举报信息表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class TReportInfo extends BaseEntity {
    /** 举报用户id */
    @Column(nullable = false)
    private Long reportId;

    /** 举报用户名 */
    @Column(nullable = false)
    private String reportName;

    /** 举报帖子id */
    @Column(nullable = false)
    private String postId;

    /** 被举报用户id */
    @Column(nullable = false)
    private String userId;

    /** 被举报用户名 */
    @Column(nullable = false)
    private String userName;

    /** 举报说明 */
    @Column(nullable = false)
    private String reportDesc;

    /** 图片id（多张） */
    private String picId;

    /** 状态（待审核、取消、通过、不通过） */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int reviewState = 0;

    /** 审核人id */
    private Long reviewerId;

}
