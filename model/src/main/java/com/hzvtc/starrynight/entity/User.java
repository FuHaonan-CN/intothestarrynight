package com.hzvtc.starrynight.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hzvtc.starrynight.comm.Const;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户表
 *
 * @author FHN
 * @date 2019/4/5 14:37
 */
@Entity
@Getter
@Setter
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "postCache")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Where(clause="is_Del = 0")
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
    @JsonIgnore
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
//    @JsonIgnoreProperties(value = {"users"})
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonIgnoreProperties(value = {"users"})
//    @JoinTable(name = "UserRole",
//            joinColumns = {@JoinColumn(name = "userId", nullable = false, updatable = false)},
//            inverseJoinColumns = {@JoinColumn(name = "roleId", nullable = false, updatable = false)})
    private Set<Role> roles = new HashSet<>();

    /**
     * 是否有效
     */
    @Column(nullable = false, columnDefinition = "tinyint default 0")
    private int isDel = 0;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    public User() {
        super();
    }

    /**
     * 密码盐.
     * @return String
     */
    public String creatCredentialsSalt(){
        return Const.PASSWORD_KEY + this.salt;
    }

    //重写hashCode方法
//    public int hashcode() {
//        return code*name.hashCode();
//    }

    //重写equals方法
//    public boolean equals(Object o) {
//        if(o instanceof Student){
//            Student stu=(Student) o;
//            return name.equalsIgnoreCase(stu.getName().trim());
//        }
//        return false;
//    }


    @Override
    public String toString() {
        return "User{" +
                "id" + super.getId() +
                "createDate" + super.getCreateDate() +
                "modifyDate" + super.getModifyDate() +
                "userName='" + userName + '\'' +
                ", userPassWord='" + userPassWord + '\'' +
                ", salt='" + salt + '\'' +
                ", actualName='" + actualName + '\'' +
                ", userSex=" + userSex +
                ", birthPlace='" + birthPlace + '\'' +
                ", nationality='" + nationality + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", picId=" + picId +
                ", isDel=" + isDel +
                ", loginTime=" + loginTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
