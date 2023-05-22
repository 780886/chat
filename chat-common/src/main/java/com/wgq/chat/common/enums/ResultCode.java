package com.wgq.chat.common.enums;

public enum ResultCode implements StatusCode {

    SUCCESS(1000, "请求成功"),
    FAILED(1001, "请求失败"),
    VALIDATE_ERROR(1002, "参数校验失败"),
    RESPONSE_PACK_ERROR(1003, "response返回包装失败"),
    USER_NAME_EXIST(1001, "用户名已存在"),
    USER_EMAIL_EXIST(2001, "用户邮箱已存在");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
