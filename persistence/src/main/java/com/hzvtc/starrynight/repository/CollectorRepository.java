package com.hzvtc.starrynight.repository;

/**
 * @Description: 帖子收藏表PostCollector数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
public interface CollectorRepository {

    Long getMostCollectUser();

    Long getMostFollowedUser(Long notUserId);

    Long getMostPraisedUser(String notUserIds);

    Long getMostCommentedUser(String notUserIds);

    Long getMostPopularUser(String notUserIds);

    Long getMostActiveUser(String notUserIds);

}
