package com.wgq.chat.controller;

import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;
import com.wgq.chat.service.RegisterService;
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

    @PostMapping("/user-name-register")
    @ApiOperation(value = "用户名注册用户")
    public void userNameRegister(@RequestBody UserNameRegisterParam userNameRegisterParam) {
        registerService.userNameRegister(userNameRegisterParam);
    }

    @PostMapping("/email-register")
    @ApiOperation(value = "邮箱注册用户")
    public LoginDTO emailRegister(@RequestBody EmailRegisterParam emailRegisterParam) {
        LoginDTO loginDTO = registerService.emailRegister(emailRegisterParam);
        return loginDTO;
    }

    @PostMapping("/phone-register")
    @ApiOperation(value = "手机号注册用户")
    public void phoneRegister(@RequestBody MobileRegisterParam mobileRegisterParam) {
        registerService.phoneRegister(mobileRegisterParam);
    }

}
