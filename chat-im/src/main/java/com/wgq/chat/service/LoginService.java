package com.wgq.chat.service;

import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.query.UserLoginQuery;
import io.jsonwebtoken.Claims;

public interface LoginService {

    String getValidSubject(String token);

    LoginDTO login(UserLoginQuery loginQuery) throws BusinessException;
}
