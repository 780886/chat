package com.wgq.chat.common.enums;

/**
 *
 *
 *
 */
public enum BusinessCodeEnum implements StatusCode {


//    系统
    SUCCESS(200, "请求成功"),
    FAILED(500, "请求失败"),
    PARAM_NOT_EMPTY(1001, "参数不能为空，请重新输入!"),
    REQUEST_PARAM_INCOMPLETE(1002, "请求参数不完整，请重新输入!"),
    RESPONSE_PACK_ERROR(1040, "response返回包装失败!"),
    TO_MANY_REQUEST(1060,"请求流量过大，请稍后再试!"),
    UN_KNOW_ERROR(1080,"系统未知异常!"),
//    验证码
    CAPTCHA_CODE_NOT_EMPTY(1101, "验证码不能为空,请重新输入验证码!"),
    CAPTCHA_CODE_NOT_EXIST(1102, "验证码不存在或已过期，请重新获取验证码!"),
    CAPTCHA_CODE_ERROR(1103, "验证码不正确，请重新输入!"),
    //短信验证码
    SMS_CODE_NOT_EMPTY(1110, "短信验证码不能为空,请重新输入验证码!"),
    SMS_CODE_NOT_EXIST(1111, "短信验证码不存在或已过期,请检查次数或重新获取验证码!"),
    SMS_CODE_VALIDATE_ERROR(1112,"短信验证码验证失败,请重新输入!"),
    SMS_CODE_ERROR(1113, "验证码获取频率太高,稍后再试!"),
//    手机号
    PHONE_NOT_EMPTY(1201, "手机号不能为空,请重新输入!"),
    PHONE_EXIST_ERROR(1202, "手机号已存在,,请重新输入!"),
    PHONE_FORMAT_ERROR(1203, "手机号格式不正确,请重新输入!"),
//    邮箱
    EMAIL_NOT_EMPTY(1301, "邮箱不能为空,请重新输入!"),
    EMAIL_FORMAT_ERROR(1302, "邮箱格式不正确，请重新输入!"),
    EMAIL_EXIST_ERROR(1303, "邮箱已存在，请重新输入!"),
    //用户
    USERNAME_NOT_EMPTY(1401, "用户名不能为空,请重新输入!"),
    USER_NAME_FORMAT_ERROR(1402, "用户名格式不正确，请重新输入!"),
    USER_NAME_EXIST_ERROR(1403, "用户名已存在，请重新输入!"),
    //密码
    PASSWORD_NOT_EMPTY(1501, "密码不能为空,请重新输入!"),
    PASSWORD_FORMAT_ERROR(1502, "密码格式不正确,请重新输入!"),
    PASSWORD_NOT_EQUAL(1503, "两次输入的密码不一致，请重新输入!"),
    USERNAME_PASSWORD_ERROR(1504, "用户名或密码错误，请重新输入!");



    private int code;
    private String msg;

    BusinessCodeEnum(int code, String msg){
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
