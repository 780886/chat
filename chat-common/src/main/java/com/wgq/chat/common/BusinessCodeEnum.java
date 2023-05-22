package com.wgq.chat.common;

public enum BusinessCodeEnum implements StatusCode {


    USER_NAME_EXIST(1001, "User name exist"),
    USER_EMAIL_EXIST(2001, "User email exist");

    private int code;
    private String message;

    BusinessCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }


    public String getMsg() {
        return this.message;
    }
}
