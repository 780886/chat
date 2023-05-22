package com.wgq.chat.service.impl;

import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.query.UserLoginQuery;
import com.wgq.chat.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String emailRegister(EmailRegisterParam emailRegisterParam) {
        return null;
    }

    @Override
    public String login(UserLoginQuery loginQuery) {
        return null;
    }
}
