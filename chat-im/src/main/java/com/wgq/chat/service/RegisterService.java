package com.wgq.chat.service;

import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;
import com.wgq.chat.pojo.vo.LoginUser;
import com.wgq.chat.protocol.ClientInformation;
import com.wgq.chat.protocol.LoginUserStatus;

public interface RegisterService {

    LoginDTO emailRegister(EmailRegisterParam emailRegisterParam, ClientInformation client) throws BusinessException;

    void phoneRegister(MobileRegisterParam mobileRegisterParam) throws BusinessException;

    void userNameRegister(UserNameRegisterParam userNameRegisterParam) throws BusinessException;

    String sign(LoginUser loginUser, LoginUserStatus loginUserStatus);

    void sendActivateEmail(EmailRegisterParam emailRegisterParam);

    void activateEmail(String token, ClientInformation client);
}
