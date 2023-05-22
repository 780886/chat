package com.wgq.chat.po.param;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmailRegisterParam {
    /**
     * 邮箱注册入参
     */

    private String email;
    private String password;
    private String userName;
    private String confirmPassword;
    private String captcha;
    private String channel;

}
