package com.hzvtc.starrynight.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/5/5 12:44
 */
@Getter
@Setter
public class ResUser extends ResBase {
    /**
     * 用户名(唯一)
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String actualName;

    /**
     * 性别
     */
    private int userSex;

    /**
     * 籍贯
     */
    private String birthPlace;

    /**
     * 民族
     */
    private String nationality;

    /**
     * 电话区域
     */
    private String areaCode;

    /**
     * 电话
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 图片id
     */
    private Long picId;

    /**
     * 角色id 立即从数据库中进行加载数据
     */
    private Set<ResRole> roles = new HashSet<>();

    /**
     * 是否有效
     */
    private int isDel;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    public ResUser() {
        super();
    }
}
