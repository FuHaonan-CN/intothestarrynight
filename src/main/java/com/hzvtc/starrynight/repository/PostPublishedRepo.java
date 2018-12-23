package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.TPostPublished;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 帖子发布表PostPublished数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
public interface PostPublishedRepo extends JpaRepository<TPostPublished, Long> {

}
