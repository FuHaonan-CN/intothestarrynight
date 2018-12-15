package com.hzvtc.starrynight.entity;

import java.io.Serializable;

/**
 * @Title: UserEntity
 * @Package: com.hzvtc.starrynight.entity
 * @Description: TODO
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 */
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /** id */
    private String id;
    /** 用户名(唯一) */
    private String userName;
    /** 密码(加密) */
    private String passWord;
    /** 图片id */
    private String picId;
    /** 真实姓名 */
    private String actualName;
    /** 性别 */
    private int userSex;
    /** 籍贯 */
    private String birthPlace;
    /** 民族 */
    private String nationality;
    /** 电话 */
    private String phone;
    /** 邮箱 */
    private String email;
    /** 是否有效 */
    private String isDel;
    /**角色id */
    private String roleId;
    /** 创建时间 */
    private String createDate;

    public UserEntity() {
        super();
    }

    public UserEntity(String userName, String passWord, int userSex) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
    }

}
