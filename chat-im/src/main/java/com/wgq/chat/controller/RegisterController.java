package com.wgq.chat.controller;

import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;
import com.wgq.chat.protocol.ClientInformation;
import com.wgq.chat.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
@Api(value = "注册相关接口")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @PostMapping("/user-name-register")
    @ApiOperation(value = "用户名注册用户")
    public void userNameRegister(@RequestBody UserNameRegisterParam userNameRegisterParam) throws BusinessException {
        registerService.userNameRegister(userNameRegisterParam);
    }

    @PostMapping("/phone-register")
    @ApiOperation(value = "手机号注册用户")
    public void phoneRegister(@RequestBody MobileRegisterParam mobileRegisterParam) throws BusinessException {
        registerService.phoneRegister(mobileRegisterParam);
    }

    @PostMapping("/email-register")
    @ApiOperation(value = "邮箱注册用户")
    public LoginDTO emailRegister(@RequestBody EmailRegisterParam emailRegisterParam, @RequestBody ClientInformation client) throws BusinessException {
        LoginDTO loginDTO = registerService.emailRegister(emailRegisterParam,client);
        return loginDTO;
    }

    @GetMapping("/email/activate/{token}")
    public void activeEmail(@PathVariable String token, @RequestBody ClientInformation client) throws BusinessException {
        registerService.activateEmail(token, client);
    }

}
