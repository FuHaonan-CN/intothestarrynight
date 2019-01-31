package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.PostInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description: 帖子发布表PostPublished数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
public interface PostInfoRepo extends JpaRepository<PostInfo, Long> {
    @Query(value = "SELECT * FROM post_info a " +
            "WHERE a.post_type=1 " +
            "ORDER BY modify_date DESC " +
            "limit 6", nativeQuery = true)
    List<PostInfo> findSixNews();

}
