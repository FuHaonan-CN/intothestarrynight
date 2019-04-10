package com.hzvtc.starrynight.utils;


import com.hzvtc.starrynight.response.Result;

/**
 * ResultUtil
 *
 * @author FHN
 * @date 2019/4/5 14:37
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("操作成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
