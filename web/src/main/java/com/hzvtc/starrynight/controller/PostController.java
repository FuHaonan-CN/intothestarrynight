package com.hzvtc.starrynight.controller;

import com.hzvtc.starrynight.comm.aop.LoggerManage;
import com.hzvtc.starrynight.entity.PostInfo;
import com.hzvtc.starrynight.entity.User;
import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.error.UserException;
import com.hzvtc.starrynight.response.ResPost;
import com.hzvtc.starrynight.response.Result;
import com.hzvtc.starrynight.service.PostService;
import com.hzvtc.starrynight.service.UserService;
import com.hzvtc.starrynight.utils.ObjectUtil;
import com.hzvtc.starrynight.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 新闻Controller .
 *
 * @author FHN
 * @version 1.0
 * @date 2019/4/21 20:54
 */
@RestController
@RequestMapping("/post")
public class PostController extends BaseController{
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }


    /**
     * post保存、修改
     *
     * @param postInfo .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @LoggerManage(description = "保存")
    @ResponseBody
    public Result save(PostInfo postInfo, @RequestParam("file") MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        //指定文件存放路径，可以是相对路径或者绝对路径
        String filePath = "./src/main/resources/templates/imgupload/";
        /*try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
        }*/
        postInfo.setPicId(111L);
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
     * 获取所有帖子列表 queryAllPost
     * @param PageIndex .
     * @param PageSize .
     * @param key .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/queryAllPost", method = RequestMethod.POST)
    @LoggerManage(description = "数据获取")
    @ResponseBody
    public Result queryAllPost(int PageIndex, int PageSize, String key) {
        PageIndex -= 1;
        Page data = postService.findRolesByKey(PageIndex, PageSize, key);
        return ResultUtil.success(data);
    }

    /**
     * 帖子通过id查找
     *
     * @param id .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @LoggerManage(description = "帖子通过id查找")
    public Result findById(Long id) throws UserException {
        PostInfo postInfo = postService.findById(id);
        if (isEmpty(postInfo)){
            throw new UserException(EmExceptionMsg.NULLRESULT, "无法查询到该帖子信息，请核对！");
        }
        ResPost resPost = new ResPost();
        BeanUtils.copyProperties(postInfo, resPost);

//        Map result = null;
//        try {
//            result = ObjectUtil.convertBean(resPost);
//        } catch (IntrospectionException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        User user = userService.findById(id);
//
//        // 存入发帖人姓名
//        result.put("author", user.getUserName());
        //存入图片真实地址
        // 。。。

        return ResultUtil.success(resPost);
    }



    /**
     * 帖子通过id逻辑删除
     *
     * @param id .
     * @return com.hzvtc.starrynight.response.Result
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @LoggerManage(description = "删除")
    public Result delete(Long id) {
        if (postService.deleteByIdFalse(id) > 0){
            return ResultUtil.success();
        } else {
            return ResultUtil.error(EmExceptionMsg.DELETE_ERR);
        }
    }

}
