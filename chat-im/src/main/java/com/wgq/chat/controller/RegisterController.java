package com.wgq.chat.controller;

import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.service.NotifyService;
import com.wgq.chat.service.RegisterService;
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
    private RegisterService registerService;

    @PostMapping("/email-register")
    @ApiOperation(value = "邮箱注册用户")
    public void emailRegister(@RequestBody EmailRegisterParam emailRegisterParam) {
        registerService.emailRegister(emailRegisterParam);
    }

    @PostMapping("/phone-register")
    @ApiOperation(value = "手机号注册用户")
    public void phoneRegister(@RequestBody PhoneRegisterParam phoneRegisterParam) {
        registerService.phoneRegister(phoneRegisterParam);
    }

}
