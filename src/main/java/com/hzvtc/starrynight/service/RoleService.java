package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.entity.User;
import org.springframework.data.domain.Page;

/**
 * @Description: RoleService
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
public interface RoleService {

    /**
     * 新增一个角色
     * @param role .
     */
    public void saveOrUpdate(Role role);

    /**
     * 根据id删除一个角色
     * @param id .
     */
    public void deleteById(Long id);

    /**
     * 关键字查询包括分页
     * @param page： 当前页
     * @param pageSize：每页显示条数
     * @param key：模糊查询关键字
     * @return Page<Role>: 角色列表
     */
    Page<Role> findRolesByKey(int page, int pageSize, String key);


}
