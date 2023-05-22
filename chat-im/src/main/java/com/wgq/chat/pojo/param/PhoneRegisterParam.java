package com.wgq.chat.pojo.param;


import lombok.Data;

@Data
public class PhoneRegisterParam {
    private String phone;
    private String passWord;
    private String UserName;
    private String confirmPassWord;
    private String captcha;
    private String channel;


}
