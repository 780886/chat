package com.wgq.chat.common;

public enum ErrorCode implements ResultCode{

    SUCCESS(200, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    PARAMS_ERROR_EMAIL_REPEAT(40000, "账号重复"),
    PARAMS_ERROR_NUL(40000, "请求参数不完整"),
    PARAMS_ERROR_EMAIL_SHORT(40000, "用户账户过短"),
    PARAMS_ERROR_PASS_SHORT(40000, "用户密码过短"),
    REPEAT_PARAMS_ERROR(40010, "账号重复"),
    NULL_ERROR(40001, "请求数据为空"),
    NOT_LOGIN(40100, "未登录"),
    NO_AUTH(40101, "无权限"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    PARAMS_ERROR_EMAIL_FORMAT(40000, "邮箱格式错误");

    private final int code;

    private final String message;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
