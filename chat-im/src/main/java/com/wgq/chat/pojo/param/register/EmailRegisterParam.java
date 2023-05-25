package com.wgq.chat.pojo.param.register;


public class EmailRegisterParam extends UserNameRegisterParam {

    /**
     * 邮箱注册入参
     */
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
