package com.wgq.chat.pojo.dto;


import com.wgq.chat.pojo.vo.LoginUser;
import com.wgq.chat.pojo.vo.VO;

public class LoginDTO implements VO, DTO {

    public LoginDTO(LoginUser loginUser, String token) {
        this.loginUser = loginUser;
        this.token = token;
    }

    private LoginUser loginUser;
    private String token;

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public String getToken() {
        return token;
    }
}
