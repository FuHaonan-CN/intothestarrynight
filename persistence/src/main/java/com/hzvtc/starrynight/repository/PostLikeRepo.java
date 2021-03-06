package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 帖子点赞表PostLike数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
public interface PostLikeRepo extends JpaRepository<PostLike, Long> {

}
