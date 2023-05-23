package com.wgq.chat.pojo.param;


import lombok.Data;

@Data
public class SendCodeParam {

    private String phone;
    private String captcha;
}
