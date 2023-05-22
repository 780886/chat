package com.wgq.chat.controller;

import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
@Api(value = "注册相关接口")
public class RegisterController {

    @Resource
    private UserService userService;

    @PostMapping("/email-register")
    @ApiOperation(value = "邮箱注册")
    public LoginToken emailRegister(@RequestBody EmailRegisterParam emailRegisterParam) {
        LoginToken loginToken = userService.emailRegister(emailRegisterParam);
        return loginToken;
    }

    @PostMapping("/phone-register")
    @ApiOperation(value = "邮箱注册")
    public LoginToken phoneRegister(@RequestBody PhoneRegisterParam phoneRegisterParam) {
        LoginToken loginToken = userService.phoneRegister(phoneRegisterParam);
        return loginToken;
    }

}
