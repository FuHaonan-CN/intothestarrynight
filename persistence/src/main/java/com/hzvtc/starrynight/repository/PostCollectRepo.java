package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.PostCollect;
import com.hzvtc.starrynight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * 帖子收藏表PostCollector数据库CRUD .
 * @author FHN
 * @date 2019/4/22 21:55
 * @version 1.0
 */
public interface PostCollectRepo extends JpaRepository<PostCollect, Long> {

    /**
     * 通过userId查询出所有收藏的帖子id
     * @param userId .
     * @return java.util.Set<com.hzvtc.starrynight.entity.PostCollect>
     */
    Set<PostCollect> findByUserId(Long userId);

    /**
     * 逻辑删除
     *
     * @param id .
     * @return int
     */
    @Modifying
    @Query("update PostCollect p set p.collectState = '1' where p.id = ?1")
    int deleteByIdFalse(long id);

    /**
     * 通过userId计算出所有收藏的帖子数
     *
     * @param userId .
     * @return int
     */
    int countByUserId(Long userId);

    /**
     * 通过postId计算出收藏数
     *
     * @param postId .
     * @return int
     */
    int countByPostId(Long postId);
}
