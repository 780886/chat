package com.wgq.chat.service.impl;

import com.google.code.kaptcha.Producer;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.common.enums.BizCodeEnum;
import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
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
        redisUtils.set(cacheKey, capText, ExpirationTimeConstants.ONE_MINUTES, TimeUnit.SECONDS);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = null;
        try {
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
    public String sendCode(String captchaKey, SendCodeParam sendCodeParam) throws BusinessException {
        String redisCaptcha = redisUtils.get(captchaKey);
        Assert.isTrue(sendCodeParam != null, "参数不能为空,请重新发送验证码");
        Assert.isTrue(sendCodeParam.getCaptcha() != null, "验证码不能为空");
        Assert.isTrue(sendCodeParam.getPhone()!= null, "手机号不能为空");
        Assert.isTrue(redisCaptcha != null,"验证码已过期,请重新获取!");
        Assert.isTrue(sendCodeParam.getCaptcha().equals(redisCaptcha),"验证码不正确,请重新输入!");
        //判断验证码是否相等
        //1、接口防刷
        String key = RedisKey.getKey(RedisKey.SMS_CODE_CACHE_PREFIX, sendCodeParam.getPhone());
        String redisCode = redisUtils.get(key);
        Assert.isTrue(redisCode != null,BizCodeEnum.SMS_CODE_NOT_EXIST.getMsg());
        //活动存入redis的时间，用当前时间减去存入redis的时间，判断用户手机号是否在60s内发送验证码
        long currentTime = Long.parseLong(redisCode.split("_")[1]);
        //60s内不能再发
        Assert.isTrue(System.currentTimeMillis() - currentTime > 60 ,"验证码获取频率太高，稍后再试");
        //2、redis.存key-phone,value-code
        String code = VerificationCodeUtil.generateVerificationCode();
        String redisStorage = code + "_" + System.currentTimeMillis();
        //存入redis，防止同一个手机号在60秒内再次发送验证码
        redisUtils.set(key, redisStorage, ExpirationTimeConstants.TEN_MINUTES, TimeUnit.SECONDS);
        log.info("发送验证码成功,手机号为:{},验证码为:{}",sendCodeParam.getPhone(),code);
        return code;
    }

}
