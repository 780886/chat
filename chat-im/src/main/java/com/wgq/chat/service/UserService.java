package com.wgq.chat.service;

import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.query.UserLoginQuery;

public interface UserService {

    String emailRegister(EmailRegisterParam emailRegisterParam);

    String login(UserLoginQuery loginQuery);

}
