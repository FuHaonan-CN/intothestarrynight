package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.entity.Role;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.error.UserException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: PostService
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
@Transactional
public interface PostService {

    /**
     * 新增一个帖子
     * @Param: post
     */
    PostInfo save(PostInfo post);

    /**
     * 根据id删除一个帖子 逻辑删除
     * @param id .
     * @return int
     */
    int deleteByIdFalse(Long id);

    /**
     * 查询最新的6条新闻
     * @Param: []
     * @Return: java.util.List
     */
    List findSixNews();

    PostInfo findById(Long userId) throws UserException;

    /**
     * 关键字查询包括分页
     * @param page： 当前页
     * @param pageSize：每页显示条数
     * @param key：模糊查询关键字
     * @return Page<Role>: 角色列表
     */
    Page<PostInfo> findRolesByKey(int page, int pageSize, String key);

}
