package com.wgq.chat.service;

import com.wgq.chat.po.LoginToken;
import com.wgq.chat.po.dto.LoginUser;
import com.wgq.chat.po.param.EmailRegisterParam;
import com.wgq.chat.po.query.UserLoginQuery;

public interface UserService {

    LoginToken emailRegister(EmailRegisterParam emailRegisterParam);

    LoginUser login(UserLoginQuery loginQuery);

}
