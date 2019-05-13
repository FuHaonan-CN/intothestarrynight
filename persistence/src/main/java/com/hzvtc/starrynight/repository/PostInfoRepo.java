package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 帖子发布表PostPublished数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
@Repository
public interface PostInfoRepo extends JpaRepository<PostInfo, Long> {
    @Query(value = "SELECT * FROM post_info a " +
            "WHERE a.post_type=1 " +
            "AND a.is_del<>0 " +
            "ORDER BY modify_date DESC " +
            "limit 6", nativeQuery = true)
    List<PostInfo> findSixNews();

    /**
     * 逻辑删除
     * @param id .
     * @return  int
     */
    @Modifying
    @Query("update PostInfo r set r.isDel = '1' where r.id = ?1")
    int deleteByIdFalse(long id);

    /**
     * 关键字查询列表包括分页
     * @param key .
     * @param pageRequest .
     * @return org.springframework.data.domain.Page<com.hzvtc.starrynight.entity.Role>
     */
    Page<PostInfo> findAllByPostTitleLike(String key, Pageable pageRequest);
}
