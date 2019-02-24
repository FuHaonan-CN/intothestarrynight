package com.hzvtc.starrynight.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @Description: 基础Entity
 * @Author: fhn
 * @Date: 2018/12/18 22:01
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Comparable<BaseEntity>,Serializable {

    private static final long serialVersionUID = 1L;
    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 创建时间 */
    @Column(nullable = false)
    private ZonedDateTime createDate;

    /** 修改时间 */
    @Column(nullable = false)
    private ZonedDateTime modifyDate;

    @PrePersist
    public void prePersist() {
        createDate = modifyDate = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        modifyDate = ZonedDateTime.now();
    }

    @Override
    public int compareTo(BaseEntity o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        return this.getId().equals(((BaseEntity) other).getId());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }*/
}
