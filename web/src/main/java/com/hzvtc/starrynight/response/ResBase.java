package com.hzvtc.starrynight.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * TODO .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/5/5 12:44
 */
@Getter
@Setter
public class ResBase implements Serializable {
    private static final long serialVersionUID = 1L;
    /** id */
    private Long id;

    /** 创建时间 */
    private ZonedDateTime createDate;

    /** 修改时间 */
    private ZonedDateTime modifyDate;

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        return this.getId().equals(((ResBase) other).getId());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
