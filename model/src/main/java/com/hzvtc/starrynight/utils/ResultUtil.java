package com.hzvtc.starrynight.utils;


import com.hzvtc.starrynight.error.EmExceptionMsg;
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
        // 该处返回的时正确信息码，为便于管理，统一加到EmExceptionMsg枚举类中
        result.setCode(EmExceptionMsg.SUCCESS.getErrCode());
        result.setMsg(EmExceptionMsg.SUCCESS.getErrMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(EmExceptionMsg e) {
        Result result = new Result();
        result.setCode(e.getErrCode());
        result.setMsg(e.getErrMsg());
        return result;
    }
}
