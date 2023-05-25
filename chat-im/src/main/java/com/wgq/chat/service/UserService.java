package com.wgq.chat.service;

import com.wgq.chat.pojo.po.User;

public interface UserService {

    Boolean existEmail(String email);

    Boolean existUserName(String userName);

    int addUser(User user);

    Boolean existMobile(String phone);

    User getUserByUserName(String userName);
}
