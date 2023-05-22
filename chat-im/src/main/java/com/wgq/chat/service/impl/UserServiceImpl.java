package com.wgq.chat.service.impl;

import com.wgq.chat.assemble.UserAssembler;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.mapper.UserMapper;
import com.wgq.chat.po.entity.User;
import com.wgq.chat.po.param.EmailRegisterParam;
import com.wgq.chat.po.query.UserLoginQuery;
import com.wgq.chat.service.UserService;
import com.wgq.chat.utils.FormatCheckUtil;
import com.wgq.chat.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private Md5DigestAsHex md5DigestAsHex;

    @Resource
    private UserMapper userMapper;

    @Override
    public String emailRegister(EmailRegisterParam emailRegisterParam) {
        Assert.isTrue(emailRegisterParam != null, "参数不能为空,请重新注册!");
        String email = emailRegisterParam.getEmail();
        String password = emailRegisterParam.getPassword();
        Assert.isTrue(FormatCheckUtil.checkEmail(email),"邮箱格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(password),"密码格式错误，请重新输入!");
        boolean existEmail = this.userMapper.existEmail(email);
        Assert.isTrue(existEmail,"邮箱已经注册,请重新输入!");
        boolean existUserName = this.userMapper.existUserName(emailRegisterParam.getUserName());
        Assert.isTrue(existUserName,"用户名已存在,请重新输入!");
        User user = UserAssembler.assembleUser(emailRegisterParam,md5DigestAsHex);
        this.userMapper.addUser(user);
        String token = jwtUtils.createToken(user.getUserId());
        return token;
    }


    @Override
    public String login(UserLoginQuery loginQuery) {
        return null;
    }
}
