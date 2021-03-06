
package com.hzvtc.starrynight.response;

import com.hzvtc.starrynight.response.error.CommonError;

/**
 * @author FHN
 */
public enum EmExceptionMsg implements CommonError{
    /**
     * TODO .
     */
    SUCCESS("000000", "操作成功"),
    FAILED("999999","操作失败"),
    ParamError("000001", "参数错误！"),

    LoginNameOrPassWordError("000100", "用户名或者密码错误！"),
    PhoneUsed("000101","该邮箱已被注册"),
    UserNameUsed("000102","该登录名称已存在"),
    EmailUsed("000103","该邮箱已被注册"),
    EmailNotRegister("000104","该邮箱地址未注册"),
    LinkOutdated("000105","该链接已过期，请重新请求"),
    PassWordError("000106","密码输入错误"),
    UserNameLengthLimit("000107","用户名长度超限"),
    LoginNameNotExists("000108","该用户未注册"),
    UserNameSame("000109","新用户名与原用户名一致"),

    FavoritesNameIsNull("000200","收藏夹名称不能为空"),
    FavoritesNameUsed("000201","收藏夹名称已被创建"),

    CollectExist("000300","该文章已被收藏"),

    FileEmpty("000400","上传文件为空"),
    LimitPictureSize("000401","图片大小必须小于2M"),
    LimitPictureType("000402","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'")
    ;


    private EmExceptionMsg(String code, String msg) {
        this.errCode = code;
        this.errMsg = msg;
    }
    private String errCode;
    private String errMsg;

    @Override
    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return null;
    }
}

