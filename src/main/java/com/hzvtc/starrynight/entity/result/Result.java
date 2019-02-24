package com.hzvtc.starrynight.entity.result;

import lombok.Getter;
import lombok.Setter;

/**
 * http请求返回的最外层对象
 * Created by fhn
 * 2017-01-21 13:34
 */
@Getter
@Setter
public class Result<T> {

    /** 错误码. */
    private int code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;

}
