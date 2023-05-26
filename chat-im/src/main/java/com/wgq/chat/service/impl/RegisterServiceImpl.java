package com.wgq.chat.service.impl;

import com.wgq.chat.assemble.UserAssembler;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.pojo.vo.LoginUser;
import com.wgq.chat.service.RegisterService;
import com.wgq.chat.service.UserService;
import com.wgq.chat.utils.FormatCheckUtil;
import com.wgq.chat.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class RegisterServiceImpl implements RegisterService {


    @Resource
    private UserService userService;

    @Resource
    private Md5DigestAsHex md5DigestAsHex;

    @Resource
    private RedisUtils redisUtils;


    @Override
    public void userNameRegister(UserNameRegisterParam userNameRegisterParam) {
        Assert.isTrue(userNameRegisterParam != null, "参数不能为空,请重新注册!");
        String userName = userNameRegisterParam.getUserName();
        String password = userNameRegisterParam.getPassword();
        String confirmPassword = userNameRegisterParam.getConfirmPassword();
        String channel = userNameRegisterParam.getChannel();
        String captcha = userNameRegisterParam.getCaptcha();
        Assert.isTrue(!StringUtils.isAnyBlank(userName,password,confirmPassword,channel),"请求参数不完整,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkUsername(userName),"用户名格式错误,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(password),"密码格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(confirmPassword),"密码格式错误，请重新输入!");
        Assert.isTrue(password.equals(confirmPassword),"两次输入的密码不一致,请重新输入!");
        Assert.isTrue(captcha != null,"验证码不能为空,请重新输入！");
        //TODO
        String code = redisUtils.get(RedisKey.SMS_CODE_CACHE_PREFIX);
        Assert.isTrue(code != null,"验证码已过期,请重新发送验证码!");
        String[] split = code.split("_");
        Assert.isTrue(!captcha.equals(split[0]),"验证码不正确,请重新输入！");
        Boolean existUserName = this.userService.existUserName(userName);
        Assert.isTrue(existUserName == null,"用户名已存在,请重新输入!");
        User user = UserAssembler.assembleUser(userNameRegisterParam,md5DigestAsHex);
        this.userService.addUser(user);

    }

    @Override
    public LoginDTO emailRegister(EmailRegisterParam emailRegisterParam) {
        Assert.isTrue(emailRegisterParam != null, "参数不能为空,请重新注册!");
        String email = emailRegisterParam.getEmail();
        String userName = emailRegisterParam.getUserName();
        String password = emailRegisterParam.getPassword();
        String confirmPassword = emailRegisterParam.getConfirmPassword();
        String channel = emailRegisterParam.getChannel();
        String captcha = emailRegisterParam.getCaptcha();
        Assert.isTrue(StringUtils.isAnyBlank(userName,email,password,confirmPassword,channel),"请求参数不完整,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkEmail(email),"邮箱格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkUsername(userName),"用户名格式错误,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(password),"密码格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(confirmPassword),"密码格式错误，请重新输入!");
        Assert.isTrue(password.equals(confirmPassword),"两次输入的密码不一致,请重新输入!");
        Boolean existEmail = this.userService.existEmail(email);
        Assert.isTrue(existEmail != null,"邮箱已经注册,请重新输入!");
        Boolean existUserName = this.userService.existUserName(userName);
        Assert.isTrue(existUserName != null,"用户名已存在,请重新输入!");
        User user = UserAssembler.assembleUser(emailRegisterParam,md5DigestAsHex);
        this.userService.addUser(user);
        LoginUser loginUser = new LoginUser.LoginUserBuild()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName("")
                .avatar("")
                .deviceId("")
                .build();
        return new LoginDTO(loginUser,"");
    }

    @Override
    public void phoneRegister(MobileRegisterParam mobileRegisterParam) {
        Assert.isTrue(mobileRegisterParam != null, "参数不能为空,请重新注册!");
        String phone = mobileRegisterParam.getMobile();
        String userName = mobileRegisterParam.getUserName();
        String password = mobileRegisterParam.getPassword();
        String confirmPassword = mobileRegisterParam.getConfirmPassword();
        String channel = mobileRegisterParam.getChannel();
        String captcha = mobileRegisterParam.getCaptcha();
        Assert.isTrue(!StringUtils.isAnyBlank(userName,phone,password,confirmPassword,channel),"请求参数不完整,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkMobile(phone),"手机号格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkUsername(userName),"用户名格式错误,请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(password),"密码格式错误，请重新输入!");
        Assert.isTrue(FormatCheckUtil.checkPassword(confirmPassword),"密码格式错误，请重新输入!");
        Assert.isTrue(password.equals(confirmPassword),"两次输入的密码不一致,请重新输入!");
        Assert.isTrue(captcha != null,"验证码不能为空,请重新输入！");
        String code = redisUtils.get(RedisKey.SMS_CODE_CACHE_PREFIX+phone);
        Assert.isTrue(code != null,"验证码已过期,请重新发送验证码!");
        String[] split = code.split("_");
        Assert.isTrue(!captcha.equals(split[0]),"验证码有误,请重新输入！");
        Boolean existMobile = this.userService.existMobile(phone);
        Assert.isTrue(existMobile == null,"手机号已经注册,请重新输入!");
        Boolean existUserName = this.userService.existUserName(userName);
        Assert.isTrue(existUserName == null,"用户名已存在,请重新输入!");
        User user = UserAssembler.assembleUser(mobileRegisterParam,md5DigestAsHex);
        this.userService.addUser(user);
    }




}
