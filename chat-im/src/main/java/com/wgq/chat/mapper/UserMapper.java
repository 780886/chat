package com.wgq.chat.mapper;

import com.wgq.chat.pojo.po.User;


public interface UserMapper {


    int addUser(User user);

    boolean existEmail(String email);

    Boolean existUserName(String userName);

    User getUserByUserName(String userName);

    Boolean existMobile(String phone);
}
