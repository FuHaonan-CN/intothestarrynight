package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.PostInfo;

import java.util.List;

/**
 * @Description: PostService
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
public interface PostService {

    /**
     * 新增一个帖子
     * @Param: post
     */
    public void save(PostInfo post);

    /**
     * 根据id删除一个帖子
     * @Param: name
     */
    public void deleteById(Long id);

    /**
     * 查询最新的6条新闻
     * @Param: []
     * @Return: java.util.List
     */
    public List findSixNews();

}
