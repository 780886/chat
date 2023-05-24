package com.wgq.chat.service;

import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;

public interface RegisterService {
    void emailRegister(EmailRegisterParam emailRegisterParam);

    void phoneRegister(PhoneRegisterParam phoneRegisterParam);
}
