package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.error.UserException;
import com.hzvtc.starrynight.response.Result;
import com.hzvtc.starrynight.service.PostService;
import com.hzvtc.starrynight.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 帖子收藏Controller .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/4/21 20:54
 */
@RestController
@RequestMapping("/postCollect")
public class PostCollectController {

    private final PostService postService;

    @Autowired
    public PostCollectController(PostService postService) {
        this.postService = postService;
    }

    /**
     * post保存、修改
     *
     * @param postInfo .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @LoggerManage(description = "保存")
    public Result save(PostInfo postInfo) {
        PostInfo save = postService.save(postInfo);

        return ResultUtil.success(save);
    }

    /**
     * 被举报后需要审核
     *
     * @param postId .
     * @param adminId .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @LoggerManage(description = "被举报后需要审核")
    public Result check(Long postId, Long adminId) throws UserException {
        PostInfo postInfo = postService.findById(postId);
        postInfo.setReviewState(1);
        postInfo.setReviewerId(adminId);
        PostInfo save = postService.save(postInfo);

        return ResultUtil.success(save);
    }

    /**
     * 帖子通过id查找
     *
     * @param id .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @LoggerManage(description = "帖子通过id查找")
    public Result findById(Long id) throws UserException {
        PostInfo postInfo = postService.findById(id);

        return ResultUtil.success(postInfo);
    }

    /**
     * 帖子通过id逻辑删除
     *
     * @param id .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @LoggerManage(description = "删除")
    public Result delete(Long id) {
        if (postService.deleteByIdFalse(id) > 0){
            return ResultUtil.success();
        } else {
            return ResultUtil.error(EmExceptionMsg.DELETE_ERR);
        }
    }

}
