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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
