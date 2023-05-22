package com.wgq.chat.mapper;

import com.wgq.chat.po.entity.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper {


    int addUser(User user);

    boolean existEmail(String email);

    boolean existUserName(String userName);
}
