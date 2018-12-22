package com.hzvtc.starrynight.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Description: 操作日志表
 * @Author: fhn
 * @Date: 2018/12/19 20:45
 */
@Entity
@Getter
@Setter
public class TLog extends BaseEntity {

    /** 操作类型 */
    @Column(nullable = false, columnDefinition="tinyint")
    private int operateType;

    /** 操作描述 */
    @Column(nullable = false)
    private String operateDesc;

    /** 操作人 */
    @Column(nullable = false)
    private Long operateId;

}
