package com.hzvtc.starrynight.handle;

import com.hzvtc.starrynight.error.EmExceptionMsg;
import com.hzvtc.starrynight.error.UserException;
import com.hzvtc.starrynight.utils.ResultUtil;
import com.hzvtc.starrynight.response.Result;
import com.hzvtc.starrynight.exception.GirlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 定义ExceptionHandle解决未被controller层吸收的exception
 *
 * @author fhn
 * @date 2018/12/14 16:36
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof UserException) {
            UserException userException = (UserException) e;
            return ResultUtil.error(userException.getErrCode(), userException.getErrMsg());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(EmExceptionMsg.UNKNOWN_ERROR);
        }
    }
}
