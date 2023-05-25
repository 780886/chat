package com.wgq.chat.service;

import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;

public interface RegisterService {

    LoginDTO emailRegister(EmailRegisterParam emailRegisterParam);

    void phoneRegister(MobileRegisterParam mobileRegisterParam);

    void userNameRegister(UserNameRegisterParam userNameRegisterParam);

}
