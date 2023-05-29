package com.wgq.chat.pojo.param.register;

import com.wgq.chat.protocol.Param;

public abstract class RegisterParam implements Param {

    private String password;
    private String confirmPassword;
    private String captcha;
    private String channel;

    public RegisterParam() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
