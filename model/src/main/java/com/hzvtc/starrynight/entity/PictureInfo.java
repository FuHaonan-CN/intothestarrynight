package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Description: 图片信息表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class PictureInfo extends BaseEntity {

    /** 图片名 */
    @Column(nullable = false)
    private String picName;

    /** 图片大小 */
    @Column(nullable = false)
    private int picSize;

    /** 图片类型 */
    @Column(nullable = false)
    private String picType;

    /** 存储地址 */
    @Column(nullable = false)
    private String picAddress;

}
