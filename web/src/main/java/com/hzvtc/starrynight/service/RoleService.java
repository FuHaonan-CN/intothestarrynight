package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @Description: RoleService
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
//@Slf4j
@Transactional
public interface RoleService{

    /**
     * 新增或修改一个角色
     * @param role .
     * @return role
     */
    Role saveOrUpdate(Role role);

    /**
     * 根据id删除一个角色
     * @param id .
     */
    void deleteById(Long id);

    /**
     * 根据id查找一个角色
     * @param id .
     * @return com.hzvtc.starrynight.entity.Role
     */
    Role findById(Long id);


    /**
     * 根据Name查找一个角色
     * @param name .
     * @return com.hzvtc.starrynight.entity.Role
     */
    Role findByRoleName(String name);

    /**
     * 根据角色id查找对应的所有用户
     * @param id .
     * @return java.util.Set<com.hzvtc.starrynight.entity.Role>
     */
    Set<Role> findRolesByPId(Long id);

    /**
     * 关键字查询包括分页
     * @param page： 当前页
     * @param pageSize：每页显示条数
     * @param key：模糊查询关键字
     * @return Page<Role>: 角色列表
     */
    Page<Role> findRolesByKey(int page, int pageSize, String key);


}
