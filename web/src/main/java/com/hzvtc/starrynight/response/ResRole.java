package com.hzvtc.starrynight.response;

import com.hzvtc.starrynight.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/5/5 12:44
 */
@Getter
@Setter
public class ResRole extends ResBase {
    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 是否有效 */
    private int isDel;

    /** 角色 - 权限关系：多对多关系 */
    private Set<Permission> permissions = new HashSet<>();

    /** 用户 - 角色关系：多对多关系 */
    private Set<ResUser> users = new HashSet<>();

}
