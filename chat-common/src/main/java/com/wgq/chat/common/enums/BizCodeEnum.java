package com.wgq.chat.common.enums;


public enum BizCodeEnum implements StatusCode {
    UN_KNOW_ERROR(10000,"系统未知异常"),
    VALID_ERROR(10001,"参数格式检验失败"),
    RESPONSE_PACK_ERROR(1004, "response返回包装失败"),
    TO_MANY_REQUEST(10002,"请求流量过大，请稍后再试"),
    SMS_CODE_ERROR(10003,"验证码获取频率太高，稍后再试!"),
    SMS_CODE_NOT_EXIST(10004,"验证码未过期或已过期，请检查次数或验证码"),
    USER_EXIST_ERROR(15001,"存在相同的用户"),
    PHONE_EXIST_ERROR(15002,"存在相同的手机号"),
    NO_STOCK_ERROR(21000,"商品库存不足"),
    USER_NAME_EXIST(15003, "用户名已存在"),
    USER_EMAIL_EXIST(15004, "用户邮箱已存在"),
    USERNAME_PASSWORD_ERROR(15003,"用户名或密码错误");


    private int code;
    private String msg;
    BizCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
