package com.hzvtc.starrynight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Title: PictureInfo
 * @Package: com.hzvtc.starrynight.entity
 * @Description: 图片信息表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
public class PictureInfo extends BaseEntity {

    /** 图片名 */
    @Column(nullable = false)
    private Long picName;

    /** 图片大小 */
    @Column(nullable = false)
    private Long picSize;

    /** 图片类型 */
    @Column(nullable = false)
    private Long picType;

    /** 存储地址 */
    @Column(nullable = false)
    private Long picAddress;

}
