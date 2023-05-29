package com.wgq.chat.utils;

import com.wgq.chat.common.EncryptionService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CaptchaUtil {


    public static String getCaptchaKey(HttpServletRequest request, EncryptionService encryptionService) {
        String ip = CommonUtils.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        // 相当于是把用户登陆的信息进行MD5加密后生成一个固定的常量值，
        // 作为Redis的键，把其设置设置成随机生成的字符串验证码
        String key = "account-service:captcha:" + encryptionService.encryptPassword(ip + userAgent);
        log.info("ip:{},userAgent:{},key:{},",ip,userAgent,key);
        return key;
    }
}
