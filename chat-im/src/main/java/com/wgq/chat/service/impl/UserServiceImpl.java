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
    public LoginToken emailRegister(EmailRegisterParam emailRegisterParam) {
        Assert.isTrue(emailRegisterParam != null, "参数不能为空,请重新注册!");
        String email = emailRegisterParam.getEmail();
        String userName = emailRegisterParam.getUserName();
        String password = emailRegisterParam.getPassword();
        String confirmPassword = emailRegisterParam.getConfirmPassword();
        String channel = emailRegisterParam.getChannel();
        String captcha = emailRegisterParam.getCaptcha();
        Assert.isTrue(StringUtils.isAnyBlank(userName,email,password,confirmPassword,channel),"请求参数不完整,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkEmail(email),"邮箱格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkUsername(userName),"用户名格式不合法：只能包含字母、数字、下划线!");
        Assert.isTrue(FormatCheckUtil.checkPassword(password),"密码格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(confirmPassword),"密码格式错误，请重新输入!");
        Assert.isTrue(password.equals(confirmPassword),"两次输入的密码不一致,请重新输入!");
        boolean existEmail = this.userMapper.existEmail(email);
        Assert.isTrue(existEmail,"邮箱已经注册,请重新输入!");
        boolean existUserName = this.userMapper.existUserName(userName);
        Assert.isTrue(existUserName,"用户名已存在,请重新输入!");
        User user = UserAssembler.assembleUser(emailRegisterParam,md5DigestAsHex);
        this.userMapper.addUser(user);
        return null;
    }


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
    public String captcha(String phone) {
        String code = VerificationCodeUtil.generateVerificationCode();
        String key = RedisKey.getKey(RedisKey.LOGIN_PHONE_PREFIX, phone);
        RedisUtils.set(key,code, ExpirationTimeConstants.THIRTY_MINUTES, TimeUnit.SECONDS);
        return code;
    }

    @Override
    public LoginToken phoneRegister(PhoneRegisterParam phoneRegisterParam) {
        Assert.isTrue(phoneRegisterParam != null, "参数不能为空,请重新注册!");
        String phone = phoneRegisterParam.getPhone();
        String userName = phoneRegisterParam.getUserName();
        String password = phoneRegisterParam.getPassword();
        String confirmPassword = phoneRegisterParam.getConfirmPassword();
        String channel = phoneRegisterParam.getChannel();
        String captcha = phoneRegisterParam.getCaptcha();
        Assert.isTrue(StringUtils.isAnyBlank(userName,phone,password,confirmPassword,channel),"请求参数不完整,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkMobile(phone),"手机号格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkUsername(userName),"用户名格式不合法：只能包含字母、数字、下划线!");
        Assert.isTrue(FormatCheckUtil.checkPassword(password),"密码格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(confirmPassword),"密码格式错误，请重新输入!");
        Assert.isTrue(password.equals(confirmPassword),"两次输入的密码不一致,请重新输入!");
        boolean existMobile = this.userMapper.existMobile(phone);
        Assert.isTrue(existMobile,"手机号已经注册,请重新输入!");
        boolean existUserName = this.userMapper.existUserName(userName);
        Assert.isTrue(existUserName,"用户名已存在,请重新输入!");
        User user = UserAssembler.assembleUser(phoneRegisterParam,md5DigestAsHex);
        this.userMapper.addUser(user);
        LoginUser loginUser = new LoginUser.LoginUserBuild()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .avatar(user.getAvatar())
                .deviceId(user.getDeviceId())
                .build();
        return null;
    }


}
