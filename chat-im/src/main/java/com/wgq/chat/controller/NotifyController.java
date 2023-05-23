package com.wgq.chat.controller;

import com.wgq.chat.service.NotifyService;
import com.wgq.chat.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
public class NotifyController {

    @Resource
    private NotifyService notifyService;

    @GetMapping("/captcha/{phone}")
    @ApiOperation(value = "发送验证码")
    public String captcha(@PathVariable String phone) {
        String captcha = notifyService.captcha(phone);
        return captcha;
    }
}
