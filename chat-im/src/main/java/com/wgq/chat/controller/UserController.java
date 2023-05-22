package com.wgq.chat.controller;

import com.wgq.chat.po.dto.LoginUser;
import com.wgq.chat.po.query.UserLoginQuery;
import com.wgq.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginUser login(@RequestBody UserLoginQuery loginQuery) {
        LoginUser loginUser =  userService.login(loginQuery);
        return loginUser;
    }
}
