package com.hzvtc.starrynight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Title: SmsRemindInfo
 * @Package: com.hzvtc.starrynight.entity
 * @Description: 短信待发提醒表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
public class SmsRemindInfo extends BaseEntity {

    /** 用户id */
    @Column(nullable = false)
    private Long reportId;

    /** 用户名 */
    @Column(nullable = false)
    private String reportName;

    /** 待提醒号码 */
    @Column(nullable = false)
    private String reportPhone;

    /** 提醒信息 */
    @Column(nullable = false)
    private String reportInfo;

    /** 提醒时间 */
    @Column(nullable = false)
    private String reportTime;

    /** 成功发送时间 */
    private String successTime;

    /** 状态（待发送、取消、成功、失败） */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int reviewState = 0;
}
