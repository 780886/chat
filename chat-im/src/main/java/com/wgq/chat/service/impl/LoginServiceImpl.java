package com.wgq.chat.service.impl;

import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.ExpirationTimeConstants;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.common.enums.BusinessCodeEnum;
import com.wgq.chat.execption.Asserts;
import com.wgq.chat.execption.BusinessException;
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
    public LoginDTO login(UserLoginQuery loginQuery) throws BusinessException {
        Asserts.isTrue(loginQuery == null, BusinessCodeEnum.PARAM_NOT_EMPTY);
        Asserts.isTrue(!FormatCheckUtil.checkEmail(loginQuery.getUserName()),BusinessCodeEnum.USER_NAME_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkEmail(loginQuery.getEmail()),BusinessCodeEnum.EMAIL_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkPassword(loginQuery.getPassword()),BusinessCodeEnum.PASSWORD_FORMAT_ERROR);
        User user = this.userService.getUserByUserName(loginQuery.getUserName());
        Asserts.isTrue(user == null,BusinessCodeEnum.USERNAME_PASSWORD_ERROR);
        String userPassword = user.getPassword();
        Asserts.isTrue(!md5DigestAsHex.verify(loginQuery.getPassword(),userPassword),BusinessCodeEnum.USERNAME_PASSWORD_ERROR);
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
