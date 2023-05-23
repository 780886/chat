package com.wgq.chat.service;

import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.pojo.param.SendCodeParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NotifyService {


    void captcha(HttpServletRequest request, HttpServletResponse response);

    String sendCode(String captchaKey, SendCodeParam sendCodeParam) throws BusinessException;
}
