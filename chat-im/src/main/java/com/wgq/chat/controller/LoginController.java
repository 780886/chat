package com.wgq.chat.controller;


import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.query.UserLoginQuery;
import com.wgq.chat.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
@Api(value = "登录相关接口")
public class LoginController {


    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public LoginDTO login(@RequestBody UserLoginQuery loginQuery) throws BusinessException {
        LoginDTO loginDTO =  loginService.login(loginQuery);
        return loginDTO;
    }


    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录")
    public void loginOut() {

    }

}
