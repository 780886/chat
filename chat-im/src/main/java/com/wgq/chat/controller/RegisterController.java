package com.wgq.chat.controller;

import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.query.UserLoginQuery;
import com.wgq.chat.service.UserService;
import com.wgq.chat.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/email-register")
    public String emailRegister(@RequestBody EmailRegisterParam emailRegisterParam) {
        String token = userService.emailRegister(emailRegisterParam);
        return token;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginQuery loginQuery) {
        String token =  userService.login(loginQuery);
        return token;
    }
}
