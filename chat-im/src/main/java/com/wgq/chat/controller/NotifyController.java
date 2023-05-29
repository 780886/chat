package com.wgq.chat.controller;

import com.wgq.chat.common.ChatEncryptionService;
import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.param.SendCodeParam;
import com.wgq.chat.service.NotifyService;
import com.wgq.chat.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("notify")
@Api(value = "通知相关接口")
public class NotifyController {

    @Resource
    private NotifyService notifyService;


    @Resource
    private ChatEncryptionService chatEncryptionService;


    @GetMapping("/captcha")
    @ApiOperation("获取图形验证码")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        notifyService.captcha(request,response);
    }

    @GetMapping("/validate-code/{captcha}")
    @ApiOperation("验证图形验证码")
    public boolean validateCode(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable String captcha) throws BusinessException {
        String captchaKey = CaptchaUtil.getCaptchaKey(request, chatEncryptionService);
        boolean  verificationResult = notifyService.validateCode(captchaKey,captcha);
        return verificationResult;
    }

    @GetMapping("/send-phone-verification-code")
    @ApiOperation(value = "发送手机验证码")
    public String sendPhoneValidateCode(HttpServletRequest request,@RequestBody SendCodeParam sendCodeParam) throws BusinessException {
        String captchaKey = CaptchaUtil.getCaptchaKey(request, chatEncryptionService);
        String code = notifyService.sendPhoneValidateCode(captchaKey,sendCodeParam);
        return code;
    }
}
