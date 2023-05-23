package com.wgq.chat.service;

import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.dto.LoginUser;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.pojo.query.UserLoginQuery;

public interface UserService {


    LoginUser login(UserLoginQuery loginQuery);


    boolean existEmail(String email);

    boolean existUserName(String userName);

    int addUser(User user);

    boolean existMobile(String phone);
}
