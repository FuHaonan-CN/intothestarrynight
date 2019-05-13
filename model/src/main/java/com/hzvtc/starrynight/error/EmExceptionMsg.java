
package com.hzvtc.starrynight.error;

/**
 * @author FHN
 */
public enum EmExceptionMsg implements CommonError {
    /**
     * 00000-操作成功
     */
    //定义通用错误类型
    SUCCESS("00000", "操作成功！"),
    /**
     * 00001-未知错误
     */
    UNKNOWN_ERROR("99999","未知错误！"),
    ParamError("00001", "参数错误！"),
    NULLRESULT("00002", "返回值为空！"),

    // 删除失败
    DELETE_ERR("00003", "删除失败！"),

    // 用户错误码10000开头
    LoginNameOrPassWordError("10001", "用户名或者密码错误！"),
    PhoneUsed("10002","该邮箱已被注册"),
    UserNameUsed("10003","该登录名称已存在"),
    EmailUsed("10004","该邮箱已被注册"),
    EmailNotRegister("10005","该邮箱地址未注册"),
    LinkOutdated("10006","该链接已过期，请重新请求"),
    PassWordError("10007","密码输入错误"),
    UserNameLengthLimit("10008","用户名长度超限"),
    LoginNameNotExists("10009","该用户未注册"),
    UserNameSame("10010","新用户名与原用户名一致"),

    // 角色错误码20000开头
    RoleNameUsed("20001","角色名称已存在"),
    FavoritesNameUsed("20002","收藏夹名称已被创建"),

    // 权限错误码30000开头
    CollectExist("30001","该文章已被收藏"),

    // 图片错误码40000开头
    FileEmpty("40001","上传文件为空"),
    LimitPictureSize("40002","图片大小必须小于2M"),
    LimitPictureType("40003","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'")

    ;

    private String errCode;
    private String errMsg;

    private EmExceptionMsg(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

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
        this.errMsg = errMsg;
        return this;
    }
}

