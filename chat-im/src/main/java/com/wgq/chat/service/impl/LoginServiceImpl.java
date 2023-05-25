package com.wgq.chat.service.impl;

import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.pojo.query.UserLoginQuery;
import com.wgq.chat.pojo.vo.LoginUser;
import com.wgq.chat.service.LoginService;
import com.wgq.chat.service.UserService;
import com.wgq.chat.utils.FormatCheckUtil;
import com.wgq.chat.utils.JwtUtil;
import com.wgq.chat.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Resource
    private Md5DigestAsHex md5DigestAsHex;

    @Resource
    private RedisUtils redisUtils;


    @Override
    public String getValidSubject(String token) {
        return JwtUtil.getJwtID(token);
    }

    @Override
    public LoginDTO login(UserLoginQuery loginQuery) {
        Assert.isTrue(loginQuery != null,"参数不能为空,请重新登录!");
        Assert.isTrue(FormatCheckUtil.checkEmail(loginQuery.getEmail()),"邮箱格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(loginQuery.getPassword()),"密码格式错误，请重新输入!");
        User user = this.userService.getUserByUserName(loginQuery.getUserName());
        Assert.isTrue(user!= null,"您输入的账户密码有误,请重新输入!");
        String userPassword = user.getPassword();
        Assert.isTrue(md5DigestAsHex.verify(loginQuery.getPassword(),userPassword),"您输入的账户密码有误,请重新输入!");
        String token = JwtUtil.buildJWT(String.valueOf(user.getUserId()));
        LoginUser loginUser = new LoginUser.LoginUserBuild()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .avatar(user.getAvatar())
                .deviceId(user.getDeviceId())
                .build();
        redisUtils.set(RedisKey.USER_TOKEN_PREFIX,token, ExpirationTimeConstants.THIRTY_MINUTES, TimeUnit.SECONDS);
        return new LoginDTO(loginUser,token);
    }
}
