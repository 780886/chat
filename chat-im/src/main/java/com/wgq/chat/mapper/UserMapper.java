package com.wgq.chat.mapper;

import com.wgq.chat.pojo.po.User;


public interface UserMapper {


    int addUser(User user);

    boolean existEmail(String email);

    boolean existUserName(String userName);

    User getUserByUserName(String userName);

    boolean existMobile(String phone);
}
