package com.hzvtc.starrynight.service;

import com.hzvtc.starrynight.entity.PostCollect;
import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.error.UserException;

import java.util.List;
import java.util.Set;

/**
 * @Description: PostService
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
public interface PostCollectService {

    /**
     * 收藏帖子
     *
     * @param postId .
     * @param userId .
     * @return com.hzvtc.starrynight.entity.PostCollect
     */
    PostCollect save(Long postId, Long userId);

    /**
     * 根据id删除一个帖子 逻辑删除
     * @param postId .
     * @param userId .
     * @return int
     */
    int deleteByIdFalse(Long postId, Long userId);

    /**
     * 通过userid查询所有收藏的帖子
     *
     * @param userId .
     * @return java.util.Set<com.hzvtc.starrynight.entity.PostInfo>
     */
    Set<PostInfo> findByUserId(Long userId) throws UserException;

    /**
     * 通过userid计算所有收藏的帖子数
     *
     * @param userId .
     * @return int
     */
    int countCollectByUserId(Long userId);

    /**
     * 通过postId计算所有收藏数
     *
     * @param postId .
     * @return int
     */
    int countCollectByPostId(Long postId);

}
