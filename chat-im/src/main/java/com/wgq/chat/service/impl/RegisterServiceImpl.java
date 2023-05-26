package com.wgq.chat.service.impl;

import com.wgq.chat.assemble.UserAssembler;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.common.constant.RedisKey;
import com.wgq.chat.common.enums.BusinessCodeEnum;
import com.wgq.chat.execption.Asserts;
import com.wgq.chat.execption.BusinessException;
import com.wgq.chat.pojo.dto.LoginDTO;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.pojo.vo.LoginUser;
import com.wgq.chat.service.RegisterService;
import com.wgq.chat.service.UserService;
import com.wgq.chat.utils.FormatCheckUtil;
import com.wgq.chat.utils.MailUtils;
import com.wgq.chat.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {


    @Resource
    private UserService userService;

    @Resource
    private Md5DigestAsHex md5DigestAsHex;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private MailUtils mailUtils;


    @Override
    public void userNameRegister(UserNameRegisterParam userNameRegisterParam) throws BusinessException {
        Asserts.isTrue(userNameRegisterParam == null, BusinessCodeEnum.PARAM_NOT_EMPTY);
        String userName = userNameRegisterParam.getUserName();
        String password = userNameRegisterParam.getPassword();
        String confirmPassword = userNameRegisterParam.getConfirmPassword();
        String channel = userNameRegisterParam.getChannel();
        String captcha = userNameRegisterParam.getCaptcha();
        Asserts.isTrue(captcha == null,BusinessCodeEnum.CAPTCHA_CODE_NOT_EMPTY);
        Asserts.isTrue(StringUtils.isAnyBlank(userName,password,confirmPassword,channel),BusinessCodeEnum.REQUEST_PARAM_INCOMPLETE);
        Asserts.isTrue(!FormatCheckUtil.checkUsername(userName),BusinessCodeEnum.USER_NAME_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkPassword(password),BusinessCodeEnum.PASSWORD_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkPassword(confirmPassword),BusinessCodeEnum.PASSWORD_FORMAT_ERROR);
        Asserts.isTrue(!password.equals(confirmPassword),BusinessCodeEnum.PASSWORD_NOT_EQUAL);
        //TODO
        String code = redisUtils.get(RedisKey.SMS_CODE_CACHE_PREFIX);
        Asserts.isTrue(code == null,BusinessCodeEnum.SMS_CODE_NOT_EXIST);
        String[] split = code.split("_");
        Asserts.isTrue(!captcha.equals(split[0]),BusinessCodeEnum.SMS_CODE_VALIDATE_ERROR);
        Boolean existUserName = this.userService.existUserName(userName);
        Asserts.isTrue(existUserName != null,BusinessCodeEnum.USER_NAME_EXIST_ERROR);
        User user = UserAssembler.assembleUser(userNameRegisterParam,md5DigestAsHex);
        this.userService.addUser(user);

    }

    @Override
    public LoginDTO emailRegister(EmailRegisterParam emailRegisterParam) throws BusinessException {
        Asserts.isTrue(emailRegisterParam == null, BusinessCodeEnum.EMAIL_FORMAT_ERROR);
        //验证validateCaptcha
        String email = emailRegisterParam.getEmail();
        String userName = emailRegisterParam.getUserName();
        String password = emailRegisterParam.getPassword();
        String confirmPassword = emailRegisterParam.getConfirmPassword();
        String channel = emailRegisterParam.getChannel();
        String captcha = emailRegisterParam.getCaptcha();
        Asserts.isTrue(StringUtils.isAnyBlank(userName,email,password,confirmPassword,channel),BusinessCodeEnum.REQUEST_PARAM_INCOMPLETE);
        Asserts.isTrue(!FormatCheckUtil.checkEmail(email),BusinessCodeEnum.EMAIL_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkUsername(userName),BusinessCodeEnum.USER_NAME_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkPassword(password),BusinessCodeEnum.PASSWORD_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkPassword(confirmPassword),BusinessCodeEnum.PASSWORD_FORMAT_ERROR);
        Asserts.isTrue(!password.equals(confirmPassword),BusinessCodeEnum.PASSWORD_NOT_EQUAL);
        Boolean existEmail = this.userService.existEmail(email);
        Asserts.isTrue(existEmail != null,BusinessCodeEnum.EMAIL_EXIST_ERROR);
        Boolean existUserName = this.userService.existUserName(userName);
        Asserts.isTrue(existUserName != null,BusinessCodeEnum.USER_NAME_EXIST_ERROR);
        User user = UserAssembler.assembleUser(emailRegisterParam,md5DigestAsHex);
        //发送激活邮件
        String activeCode = UUID.randomUUID().toString();
        String projectPath = System.getProperty("user.dir");
        String activeUrl = projectPath + "/frontdesk/member/active?activeCode=" + activeCode;
        String text = "恭喜您注册成功！<a href = '" + activeUrl + "'>点击激活</a>完成账号认证";
        mailUtils.sendMail(emailRegisterParam.getEmail(), text, "chat激活邮件");
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

    public String active(String checkCode) {
//        //根据激活码查询用户
//        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("checkCode", checkCode);
//        Member member = memberMapper.selectOne(queryWrapper);
//
//        if (member == null) {
//            return "激活失败，激活码错误！";
//        } else {
//            member.setActive(true);
//            memberMapper.updateById(member);
//            return "激活成功，请<a href='" + projectPath + "/frontdesk/login'>登录</a>";
//        }
        return null;
    }
//    public void activeEmail(String userId) {
//        // 根据userId查询用户信息
//        User user = userDao.getUserById(userId);
//        // 验证用户信息
//        if (user != null) {
//            // 更新用户状态
//            user.setStatus(1);
//            userDao.update(user);
//        }
//    }



    @Override
    public void phoneRegister(MobileRegisterParam mobileRegisterParam) throws BusinessException {
        Asserts.isTrue(mobileRegisterParam == null, BusinessCodeEnum.PARAM_NOT_EMPTY);
        String phone = mobileRegisterParam.getMobile();
        String userName = mobileRegisterParam.getUserName();
        String password = mobileRegisterParam.getPassword();
        String confirmPassword = mobileRegisterParam.getConfirmPassword();
        String channel = mobileRegisterParam.getChannel();
        String captcha = mobileRegisterParam.getCaptcha();
        Asserts.isTrue(captcha == null,BusinessCodeEnum.SMS_CODE_NOT_EMPTY);
        Asserts.isTrue(StringUtils.isAnyBlank(userName,phone,password,confirmPassword,channel),BusinessCodeEnum.REQUEST_PARAM_INCOMPLETE);
        Asserts.isTrue(!FormatCheckUtil.checkMobile(phone),BusinessCodeEnum.PHONE_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkUsername(userName),BusinessCodeEnum.USER_NAME_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkPassword(password),BusinessCodeEnum.PHONE_FORMAT_ERROR);
        Asserts.isTrue(!FormatCheckUtil.checkPassword(confirmPassword),BusinessCodeEnum.PHONE_FORMAT_ERROR);
        Asserts.isTrue(!password.equals(confirmPassword),BusinessCodeEnum.PASSWORD_NOT_EQUAL);
        String code = redisUtils.get(RedisKey.SMS_CODE_CACHE_PREFIX+phone);
        Asserts.isTrue(code == null,BusinessCodeEnum.SMS_CODE_NOT_EXIST);
        String[] split = code.split("_");
        Asserts.isTrue(!captcha.equals(split[0]),BusinessCodeEnum.SMS_CODE_VALIDATE_ERROR);
        Boolean existMobile = this.userService.existMobile(phone);
        Asserts.isTrue(existMobile != null,BusinessCodeEnum.PHONE_EXIST_ERROR);
        Boolean existUserName = this.userService.existUserName(userName);
        Asserts.isTrue(existUserName != null,BusinessCodeEnum.USER_NAME_EXIST_ERROR);
        User user = UserAssembler.assembleUser(mobileRegisterParam,md5DigestAsHex);
        this.userService.addUser(user);
    }




}
