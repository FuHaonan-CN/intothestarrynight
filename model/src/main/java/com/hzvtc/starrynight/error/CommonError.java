package com.hzvtc.starrynight.error;

/**
 * TODO .
 * @author fhn
 * @date 2019/4/10 19:36
 * @version 1.0
 */
public interface CommonError {
    /**
     * 获取错误代码
     *
     * @return int
     */
    public String getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);

}
