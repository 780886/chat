package com.wgq.chat.pojo.param;


import lombok.Data;

@Data
public class EmailRegisterParam {
    /**
     * 邮箱注册入参
     */

    private String email;
    private String passWord;
    private String UserName;
    private String confirmPassWord;
    private String captcha;
    private String channel;

}
