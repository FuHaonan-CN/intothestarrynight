package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.entity.PostCollect;
import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.error.UserException;
import com.hzvtc.starrynight.repository.PostCollectRepo;
import com.hzvtc.starrynight.repository.PostInfoRepo;
import com.hzvtc.starrynight.service.PostCollectService;
import com.hzvtc.starrynight.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class PostCollectServiceImpl implements PostCollectService {

    private final PostCollectRepo postCollectRepo;
    private final PostService postService;

    @Autowired
    public PostCollectServiceImpl(PostCollectRepo postCollectRepo, PostService postService) {
        this.postCollectRepo = postCollectRepo;
        this.postService = postService;
    }

    /**
     * TODO
     *
     * @param postId .
     * @param userId .
     * @return com.hzvtc.starrynight.entity.PostCollect
     */
    @Override
    public PostCollect save(Long postId, Long userId) {
        PostCollect postCollect = new PostCollect();
        postCollect.setPostId(postId);
        postCollect.setUserId(userId);
        return postCollectRepo.save(postCollect);
    }

    @Override
    public int deleteByIdFalse(Long postId, Long userId) {
        return 0;
    }


    @Override
    public Set<PostInfo> findByUserId(Long userId) throws UserException {
        // 通过userId查询出所有收藏的帖子id
        Set<PostCollect> collects = postCollectRepo.findByUserId(userId);

        Set<PostInfo> postInfoSet = new HashSet<>();
        for (PostCollect collect:collects) {
            postInfoSet.add(postService.findById(collect.getPostId()));
        }
        return postInfoSet;
    }

    @Override
    public int countCollectByUserId(Long userId) {
        return postCollectRepo.countByUserId(userId);
    }

    @Override
    public int countCollectByPostId(Long postId) {
        return postCollectRepo.countByPostId(postId);
    }
}