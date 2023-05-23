package com.wgq.chat.service.impl;

import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.common.enums.BizCodeEnum;
import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.service.NotifyService;
import com.wgq.chat.utils.RedisUtils;
import com.wgq.chat.utils.VerificationCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService {


    @Override
    public String captcha(String phone) {
        String code = VerificationCodeUtil.generateVerificationCode();
        String key = RedisKey.getKey(RedisKey.LOGIN_PHONE_PREFIX, phone);
        RedisUtils.set(key,code, ExpirationTimeConstants.THIRTY_MINUTES, TimeUnit.SECONDS);
        return code;
    }

    @Override
    public String sendCode(String phone) throws BusinessException {
        //1、接口防刷
        String key = RedisKey.getKey(RedisKey.SMS_CODE_CACHE_PREFIX, phone);
        String redisCode = RedisUtils.get(key);
        if (!StringUtils.isEmpty(redisCode)) {
            //活动存入redis的时间，用当前时间减去存入redis的时间，判断用户手机号是否在60s内发送验证码
            long currentTime = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - currentTime < 60000) {
                //60s内不能再发
                throw new BusinessException(BizCodeEnum.SMS_CODE_EXCEPTION);
            }
        }
        //2、验证码的再次效验 redis.存key-phone,value-code
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String codeNum = String.valueOf(code);
        String redisStorage = codeNum + "_" + System.currentTimeMillis();
//
        //存入redis，防止同一个手机号在60秒内再次发送验证码
        RedisUtils.set(key, redisStorage, ExpirationTimeConstants.TEN_MINUTES, TimeUnit.SECONDS);
        log.info("发送验证码成功,手机号为:{},验证码为:{}",phone,codeNum);
        return codeNum;
    }

}
