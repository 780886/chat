package com.wgq.chat.service;

import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;

public interface NotifyService {


    String captcha(String phone);

    String sendCode(String phone) throws BusinessException;
}
