package com.wgq.chat.po.param;


import lombok.Data;

@Data
public class PhoneRegisterParam {

    private String phone;
    private String password;
    private String userName;
    private String confirmPassword;
    private String captcha;
    private String channel;


}
