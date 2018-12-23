package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.TPostCollect;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 帖子收藏表PostCollector数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
public interface PostCollectRepo extends JpaRepository<TPostCollect, Long> {

}
