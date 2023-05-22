package com.wgq.chat.service;

import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.dto.LoginUser;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.pojo.query.UserLoginQuery;

public interface UserService {

    LoginToken emailRegister(EmailRegisterParam emailRegisterParam);

    LoginUser login(UserLoginQuery loginQuery);

    String captcha(String phone);

    LoginToken phoneRegister(PhoneRegisterParam phoneRegisterParam);

}
