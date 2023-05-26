package com.wgq.chat.service;

import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;

public interface RegisterService {

    LoginDTO emailRegister(EmailRegisterParam emailRegisterParam) throws BusinessException;

    void phoneRegister(MobileRegisterParam mobileRegisterParam) throws BusinessException;

    void userNameRegister(UserNameRegisterParam userNameRegisterParam) throws BusinessException;

}
