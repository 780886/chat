package com.wgq.chat.assemble;

import com.wgq.chat.common.Encrypt;
import com.wgq.chat.pojo.param.register.MobileRegisterParam;
import com.wgq.chat.pojo.param.register.UserNameRegisterParam;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.pojo.param.register.EmailRegisterParam;

import java.util.Date;

public class UserAssembler {


    public static User assembleUser(EmailRegisterParam emailRegisterParam, Encrypt encrypt) {
        User user = new User();
        user.setEmail(emailRegisterParam.getEmail());
        String encryptPassword = encrypt.encrypt(emailRegisterParam.getPassword());
        user.setPassword(encryptPassword);
        user.setUserName(emailRegisterParam.getUserName());
        user.setChannel(emailRegisterParam.getChannel());
        user.setStatus(0);
        user.setGmtCreate(new Date().getTime());
        user.setGmtModified(new Date().getTime());
        return user;
    }

    public static User assembleUser(MobileRegisterParam mobileRegisterParam, Encrypt encrypt) {
        User user = new User();
        user.setMobile(mobileRegisterParam.getMobile());
        String encryptPassword = encrypt.encrypt(mobileRegisterParam.getPassword());
        user.setPassword(encryptPassword);
        user.setUserName(mobileRegisterParam.getUserName());
        user.setChannel(mobileRegisterParam.getChannel());
        user.setStatus(0);
        user.setGmtCreate(new Date().getTime());
        user.setGmtModified(new Date().getTime());
        return user;
    }

    public static User assembleUser(UserNameRegisterParam userNameRegisterParam, Encrypt encrypt) {
        User user = new User();
        String encryptPassword = encrypt.encrypt(userNameRegisterParam.getPassword());
        user.setPassword(encryptPassword);
        user.setUserName(userNameRegisterParam.getUserName());
        user.setChannel(userNameRegisterParam.getChannel());
        user.setStatus(0);
        user.setGmtCreate(new Date().getTime());
        user.setGmtModified(new Date().getTime());
        return user;
    }


}
