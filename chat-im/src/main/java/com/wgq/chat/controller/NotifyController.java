package com.wgq.chat.controller;

import com.google.code.kaptcha.Producer;
import com.wgq.chat.common.Encrypt;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.param.SendCodeParam;
import com.wgq.chat.service.NotifyService;
import com.wgq.chat.service.UserService;
import com.wgq.chat.utils.CaptchaUtil;
import com.wgq.chat.utils.CommonUtils;
import com.wgq.chat.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("notify")
@Api(value = "通知相关接口")
public class NotifyController {

    @Resource
    private NotifyService notifyService;


    @Resource
    private Md5DigestAsHex md5DigestAsHex;


    @GetMapping("/captcha")
    @ApiOperation("获取图形验证码")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        notifyService.captcha(request,response);
    }

    @GetMapping("/validate-code/{captcha}")
    @ApiOperation("验证图形验证码")
    public boolean validateCode(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable String captcha) throws BusinessException {
        String captchaKey = CaptchaUtil.getCaptchaKey(request,md5DigestAsHex);
        boolean  verificationResult = notifyService.validateCode(captchaKey,captcha);
        return verificationResult;
    }

    @GetMapping("/send-phone-verification-code")
    @ApiOperation(value = "发送手机验证码")
    public String sendPhoneValidateCode(HttpServletRequest request,@RequestBody SendCodeParam sendCodeParam) throws BusinessException {
        String captchaKey = CaptchaUtil.getCaptchaKey(request,md5DigestAsHex);
        String code = notifyService.sendPhoneValidateCode(captchaKey,sendCodeParam);
        return code;
    }
}
