package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.repository.PostInfoRepo;
import com.hzvtc.starrynight.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class PostServiceImpl implements PostService {
    private final PostInfoRepo postInfoRepo;

    @Autowired
    public PostServiceImpl(PostInfoRepo PostPublishedInfoRepo) {
        this.postInfoRepo = PostPublishedInfoRepo;
    }

    /**
     * 新增一个帖子
     * @Param: User
     */
    @Override
    public void save(PostInfo post){
        postInfoRepo.save(post);
    }

    /**
     * 根据name删除一个用户高
     * @Param: name
     */
    @Override
    public void deleteById(Long id){
        postInfoRepo.deleteById(id);
    }

    @Override
    public List findSixNews() {
        return postInfoRepo.findSixNews();
    }

}