package com.hzvtc.starrynight.exception;


import com.hzvtc.starrynight.enums.ResultEnum;

/**
 * @Description: JPA使用原生SQL基类
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public class GirlException extends RuntimeException{

    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
