package com.wgq.chat.controller;

import com.wgq.chat.pojo.dto.LoginUser;
import com.wgq.chat.pojo.query.UserLoginQuery;
import com.wgq.chat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(value = "用户相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public LoginUser login(@RequestBody UserLoginQuery loginQuery) {
        LoginUser loginUser =  userService.login(loginQuery);
        return loginUser;
    }

    @GetMapping("/captcha/{phone}")
    public String captcha(@PathVariable String phone) {
        String captcha = userService.captcha(phone);
        return captcha;
    }
}
