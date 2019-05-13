package com.hzvtc.starrynight.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/5/5 12:44
 */
@Getter
@Setter
public class ResPost extends ResBase {
    /** 发帖人id */
    private Long userId;

    /** 发帖人姓名 */
    private String userName;

    /** 帖子标题 */
    private String postTitle;

    /** 内容 */
    private String postContent;

    /** 图片id */
    private Long picId;

    /** 点赞数 */
    private int likeNum = 0;

    /** 收藏数 */
    private int collectNum = 0;

    /** 审核人id */
    private Long reviewerId;

    /** 审核人姓名 */
    private String reviewerName;

    /** 审核状态 */
    private int reviewState = 0;

    /** 帖子类型 0：普通用户 1：最新新闻 2：倒计时事件 */
    private int postType = 0;

}
