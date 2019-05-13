package com.hzvtc.starrynight.service.impl;

import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.error.UserException;
import com.hzvtc.starrynight.repository.PostInfoRepo;
import com.hzvtc.starrynight.service.BaseService;
import com.hzvtc.starrynight.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
@Service
public class PostServiceImpl extends BaseServiceImpl implements PostService {
    private final PostInfoRepo postInfoRepo;

    @Autowired
    public PostServiceImpl(PostInfoRepo postInfoRepo) {
        this.postInfoRepo = postInfoRepo;
    }

    /**
     * 新增一个帖子
     * @Param: User
     */
    @Override
//    @Transactional
    public PostInfo save(PostInfo post){
        if (isEmpty(post.getId())){
            return postInfoRepo.save(post);
        } else {
            PostInfo post1 = findById(post.getId());
            post1.setPostTitle(post.getPostTitle());
            post1.setPostType(post.getPostType());
            post1.setPostContent(post.getPostContent());
            return postInfoRepo.save(post1);
        }
    }

    /**
     * 根据name删除一个
     */
    @Override
    public int deleteByIdFalse(Long id){
        return postInfoRepo.deleteByIdFalse(id);
    }

    @Override
    public List findSixNews() {
        return postInfoRepo.findSixNews();
    }

    @Override
    public PostInfo findById(Long id) {
        Optional<PostInfo> postInfo = postInfoRepo.findById(id);
        return postInfo.orElse(null);
    }

    @Override
    public Page<PostInfo> findRolesByKey(int page, int pageSize, String key) {
        return postInfoRepo.findAllByPostTitleLike("%"+key+"%", PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));
    }
}