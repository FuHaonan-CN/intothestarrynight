package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.text.SimpleDateFormat;

/**
 * @Title: User
 * @Package: com.hzvtc.starrynight.entity
 * @Description: 用户表
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 */
@Entity
@Getter
@Setter
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "postCache")
public class User extends BaseEntity {

    private static final SimpleDateFormat SLUG_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    /** 用户名(唯一) */
    @Column(nullable = false)
    private String userName;
    /** 密码(加密) */
    @Column(nullable = false)
    private String userPassWord;
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
    /** 图片id */
    private String picId;
    /**角色id */
    @Column(nullable = false)
    private String roleId;
    /** 是否有效 */
    @Column(nullable = false, columnDefinition="tinyint default 0")
    private int isDel = 0;

    public User() {
        super();
    }

}
