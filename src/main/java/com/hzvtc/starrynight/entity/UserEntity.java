package com.hzvtc.starrynight.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Title: UserEntity
 * @Package: com.hzvtc.starrynight.entity
 * @Description: TODO
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 */
@Entity
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /** id */
    @Id
    @GeneratedValue
    private String xy0100;
    /** 用户名(唯一) */
    private String xy0101;
    /** 密码(加密) */
    private String xy0102;
    /** 真实姓名 */
    private String xy0103;
    /** 性别 */
    private int xy0104;
    /** 籍贯 */
    private String xy0105;
    /** 民族 */
    private String xy0106;
    /** 电话 */
    private String xy0107;
    /** 邮箱 */
    private String xy0108;
    /** 图片id */
    private String xy1000;
    /** 是否有效 */
    private String isDel;
    /**角色id */
    private String xy0200;
    /** 创建时间 */
    private String createDate;

    public UserEntity() {
        super();
    }

    public UserEntity(String xy0101, String xy0102, String xy0103) {
        this.xy0101 = xy0101;
        this.xy0102 = xy0102;
        this.xy0103 = xy0103;
    }

    public String getXy0100() {
        return xy0100;
    }

    public void setXy0100(String xy0100) {
        this.xy0100 = xy0100;
    }

    public String getXy0101() {
        return xy0101;
    }

    public void setXy0101(String xy0101) {
        this.xy0101 = xy0101;
    }

    public String getXy0102() {
        return xy0102;
    }

    public void setXy0102(String xy0102) {
        this.xy0102 = xy0102;
    }

    public String getXy0103() {
        return xy0103;
    }

    public void setXy0103(String xy0103) {
        this.xy0103 = xy0103;
    }

    public int getXy0104() {
        return xy0104;
    }

    public void setXy0104(int xy0104) {
        this.xy0104 = xy0104;
    }

    public String getXy0105() {
        return xy0105;
    }

    public void setXy0105(String xy0105) {
        this.xy0105 = xy0105;
    }

    public String getXy0106() {
        return xy0106;
    }

    public void setXy0106(String xy0106) {
        this.xy0106 = xy0106;
    }

    public String getXy0107() {
        return xy0107;
    }

    public void setXy0107(String xy0107) {
        this.xy0107 = xy0107;
    }

    public String getXy0108() {
        return xy0108;
    }

    public void setXy0108(String xy0108) {
        this.xy0108 = xy0108;
    }

    public String getXy1000() {
        return xy1000;
    }

    public void setXy1000(String xy1000) {
        this.xy1000 = xy1000;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getXy0200() {
        return xy0200;
    }

    public void setXy0200(String xy0200) {
        this.xy0200 = xy0200;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
