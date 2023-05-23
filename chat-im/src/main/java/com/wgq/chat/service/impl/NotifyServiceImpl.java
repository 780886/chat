package com.wgq.chat.service.impl;

import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.service.NotifyService;
import com.wgq.chat.utils.RedisUtils;
import com.wgq.chat.utils.VerificationCodeUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class NotifyServiceImpl implements NotifyService {


    @Override
    public String captcha(String phone) {
        String code = VerificationCodeUtil.generateVerificationCode();
        String key = RedisKey.getKey(RedisKey.LOGIN_PHONE_PREFIX, phone);
        RedisUtils.set(key,code, ExpirationTimeConstants.THIRTY_MINUTES, TimeUnit.SECONDS);
        return code;
    }

}
