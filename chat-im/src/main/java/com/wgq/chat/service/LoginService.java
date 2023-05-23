package com.wgq.chat.service;

import com.wgq.chat.pojo.dto.LoginUser;
import com.wgq.chat.pojo.query.UserLoginQuery;

public interface LoginService {

    String getValidSubject(String token);

    LoginUser login(UserLoginQuery loginQuery);
}
