package com.wgq.chat.service.impl;

import com.google.code.kaptcha.Producer;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.common.enums.BusinessCodeEnum;
import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.param.SendCodeParam;
import com.wgq.chat.service.NotifyService;
import com.wgq.chat.utils.CaptchaUtil;
import com.wgq.chat.utils.RedisUtils;
import com.wgq.chat.utils.StringUtils;
import com.wgq.chat.utils.VerificationCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private Producer captchaProducer;

    @Resource
    private Md5DigestAsHex md5DigestAsHex;


    @Override
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        String cacheKey = CaptchaUtil.getCaptchaKey(request,md5DigestAsHex);
        String capText = captchaProducer.createText();
        log.info("验证码生成完毕，回显到屏幕上:{}",capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = null;
        try {
            redisUtils.set(cacheKey, capText, ExpirationTimeConstants.ONE_MINUTES, TimeUnit.SECONDS);
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "create_date-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            log.error("获取验证码失败:{}", e.getMessage());
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String sendPhoneValidateCode(String captchaKey, SendCodeParam sendCodeParam) throws BusinessException {
        Assert.isTrue(sendCodeParam != null, "参数不能为空,请重新发送验证码");
        Assert.isTrue(sendCodeParam.getCaptcha() != null, "验证码不能为空");
        boolean validateResult = validateCode(captchaKey, sendCodeParam.getCaptcha());
        Assert.isTrue(validateResult,"验证码不正确,请重新输入!");
        Assert.isTrue(sendCodeParam.getPhone()!= null, "手机号不能为空");
        //1、接口防刷
        String redisCode = redisUtils.get(RedisKey.SMS_CODE_CACHE_PREFIX+sendCodeParam.getPhone());
        if (!StringUtils.isNullOrEmpty(redisCode)){
            //活动存入redis的时间，用当前时间减去存入redis的时间，判断用户手机号是否在60s内发送验证码
            String[] split = redisCode.split("_");
            long currentTime = Long.parseLong(split[1]);
            if (System.currentTimeMillis() - currentTime < 60000){
                throw new BusinessException(BusinessCodeEnum.SMS_CODE_ERROR);
            }
        }
        //2、redis.存key-phone,value-code
        String code = VerificationCodeUtil.generateVerificationCode();
        String redisStorage = code + "_" + System.currentTimeMillis();
        //存入redis，防止同一个手机号在60秒内再次发送验证码
        redisUtils.set(RedisKey.SMS_CODE_CACHE_PREFIX + sendCodeParam.getPhone(), redisStorage, ExpirationTimeConstants.ONE_MINUTES, TimeUnit.SECONDS);
        redisUtils.del(captchaKey);
        log.info("发送验证码成功,手机号为:{},验证码为:{}",sendCodeParam.getPhone(),code);
        return code;
    }

    @Override
    public boolean validateCode(String captchaKey, String captcha) {
        String redisCaptcha = redisUtils.get(captchaKey);
        Assert.isTrue(redisCaptcha != null,"验证码已过期,请重新获取!");
        return redisCaptcha.equals(captcha);
    }
}
