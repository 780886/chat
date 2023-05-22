package com.wgq.chat.service.impl;

import com.wgq.chat.assemble.UserAssembler;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.mapper.UserMapper;
import com.wgq.chat.po.LoginToken;
import com.wgq.chat.po.dto.LoginUser;
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
    public LoginToken emailRegister(EmailRegisterParam emailRegisterParam) {
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
        LoginToken loginToken = new LoginToken(token);
        return loginToken;
    }


    @Override
    public LoginUser login(UserLoginQuery loginQuery) {
        Assert.isTrue(loginQuery != null,"参数不能为空,请重新登录!");
        Assert.isTrue(FormatCheckUtil.checkEmail(loginQuery.getEmail()),"邮箱格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(loginQuery.getPassword()),"密码格式错误，请重新输入!");
        User user = this.userMapper.getUserByUserName(loginQuery.getUserName());
        Assert.isTrue(user!= null,"您输入的账户密码有误,请重新输入!");
        String userPassword = user.getPassword();
//        Assert.isTrue(md5DigestAsHex.encrypt(loginQuery.getPassword()));
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(user.getUserName());
        loginUser.setUserId(user.getUserId());
        return loginUser;
    }
}
