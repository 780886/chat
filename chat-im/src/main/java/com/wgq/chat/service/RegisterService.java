package com.wgq.chat.service;

import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;

public interface RegisterService {
    LoginToken emailRegister(EmailRegisterParam emailRegisterParam);

    LoginToken phoneRegister(PhoneRegisterParam phoneRegisterParam);
}
