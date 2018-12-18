package com.hzvtc.starrynight.entity;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Title: BaseEntity
 * @Package: com.hzvtc.starrynight.entity
 * @Description: 基础Entity
 * @Author: fhn
 * @Date: 2018/12/18 22:01
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
