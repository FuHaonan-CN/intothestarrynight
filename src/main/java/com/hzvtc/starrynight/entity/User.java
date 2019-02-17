package com.hzvtc.starrynight.entity;

import com.hzvtc.starrynight.comm.Const;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.List;

/**
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

    /**
     * 用户名(唯一)
     */
    @Column(nullable = false, unique = true)
    private String userName;

    /**
     * 密码(加密)
     */
    @Column(nullable = false)
    private String userPassWord;

    /**
     * 加密密码的盐
     */
    private String salt;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roleList;
    //    @Column(nullable = false)
//    private Long roleId;

    /**
     * 是否有效
     */
    @Column(nullable = false, columnDefinition = "tinyint default 0")
    private int isDel = 0;

    /**
     * 登录时间
     */
    private ZonedDateTime loginTime;

    /**
     * 上次登录时间
     */
    private ZonedDateTime lastLoginTime;

    public User() {
        super();
    }
    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return Const.PASSWORD_KEY + this.salt;
    }
}
