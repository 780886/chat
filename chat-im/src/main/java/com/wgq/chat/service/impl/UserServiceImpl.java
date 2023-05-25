package com.wgq.chat.service.impl;

import com.wgq.chat.mapper.UserMapper;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public Boolean existEmail(String email) {
        return this.userMapper.existEmail(email);
    }

    @Override
    public Boolean existUserName(String userName) {
        return this.userMapper.existUserName(userName);
    }

    @Override
    public int addUser(User user) {
        return this.userMapper.addUser(user);
    }

    @Override
    public Boolean existMobile(String phone) {
        return this.userMapper.existMobile(phone);
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userMapper.getUserByUserName(userName);
    }


}
