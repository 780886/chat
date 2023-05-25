package com.wgq.chat.service;

import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.query.UserLoginQuery;

public interface LoginService {

    String getValidSubject(String token);

    LoginDTO login(UserLoginQuery loginQuery);
}
