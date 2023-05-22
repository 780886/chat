package com.wgq.chat.controller;

import com.wgq.chat.po.param.EmailRegisterParam;
import com.wgq.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    private UserService userService;

    @PostMapping("/email-register")
    public String emailRegister(@RequestBody EmailRegisterParam emailRegisterParam) {
        String token = userService.emailRegister(emailRegisterParam);
        return token;
    }

}
