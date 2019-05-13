package com.hzvtc.starrynight.error;

/**
 * TODO .
 * 包装器业务异常实现
 *
 * @author FHN
 * @date 2019/4/10 22:41
 */
public class UserException extends Exception implements CommonError {

    private CommonError commonError;

    /**
     * 直接接受EmException的传参用于构造业务异常
     * @param commonError .
     */
    public UserException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }
    /**
     * 接收自定义errMsg的方式构造业务异常
     * @param commonError .
     * @param errMsg .
     */
    public UserException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public String getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
