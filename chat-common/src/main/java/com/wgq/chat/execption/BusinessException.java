package com.wgq.chat.execption;

import com.wgq.chat.common.enums.StatusCode;

public class BusinessException extends Exception {


    private Integer code;
    private String msg;

    public BusinessException(StatusCode statusCode,String message) {
        super(message);
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public BusinessException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.code = statusCode.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
