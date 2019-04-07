package com.hzvtc.starrynight.enums;

/**
 * 枚举类
 *
 * @author FHN
 * @date 2019/4/5 14:37
 */
public enum ResultEnum {
    /** -1 未知错误 */
    UNKONW_ERROR(-1, "未知错误"),
    /** 0 成功提示 */
    SUCCESS(0, "成功"),
    /** 100 xxx */
    PRIMARY_SCHOOL(100, "我猜你可能还在上小学"),
    /** 101 xxx */
    MIDDLE_SCHOOL(101, "你可能在上初中"),

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
