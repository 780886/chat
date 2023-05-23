package com.wgq.chat.service.impl;

import com.wgq.chat.assemble.UserAssembler;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.mapper.UserMapper;
import com.wgq.chat.pojo.LoginToken;
import com.wgq.chat.pojo.dto.LoginUser;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.pojo.param.EmailRegisterParam;
import com.wgq.chat.pojo.query.UserLoginQuery;
import com.wgq.chat.service.UserService;
import com.wgq.chat.utils.FormatCheckUtil;
import com.wgq.chat.utils.JwtUtil;
import com.wgq.chat.utils.RedisUtils;
import com.wgq.chat.utils.VerificationCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private Md5DigestAsHex md5DigestAsHex;

    @Resource
    private UserMapper userMapper;


    @Override
    public LoginUser login(UserLoginQuery loginQuery) {
        Assert.isTrue(loginQuery != null,"参数不能为空,请重新登录!");
        Assert.isTrue(FormatCheckUtil.checkEmail(loginQuery.getEmail()),"邮箱格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(loginQuery.getPassword()),"密码格式错误，请重新输入!");
        User user = this.userMapper.getUserByUserName(loginQuery.getUserName());
        Assert.isTrue(user!= null,"您输入的账户密码有误,请重新输入!");
        String userPassword = user.getPassword();
        Assert.isTrue(md5DigestAsHex.verify(loginQuery.getPassword(),userPassword),"您输入的账户密码有误,请重新输入!");
        LoginUser loginUser = new LoginUser.LoginUserBuild()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .avatar(user.getAvatar())
                .deviceId(user.getDeviceId())
                .build();
        return loginUser;
    }

    @Override
    public boolean existEmail(String email) {
        return this.userMapper.existEmail(email);
    }

    @Override
    public boolean existUserName(String userName) {
        return this.userMapper.existUserName(userName);
    }

    @Override
    public int addUser(User user) {
        return this.userMapper.addUser(user);
    }

    @Override
    public boolean existMobile(String phone) {
        return this.userMapper.existMobile(phone);
    }


}
