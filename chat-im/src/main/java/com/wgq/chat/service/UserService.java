package com.wgq.chat.service;

import com.wgq.chat.po.param.EmailRegisterParam;
import com.wgq.chat.po.query.UserLoginQuery;

public interface UserService {

    String emailRegister(EmailRegisterParam emailRegisterParam);

    String login(UserLoginQuery loginQuery);

}
