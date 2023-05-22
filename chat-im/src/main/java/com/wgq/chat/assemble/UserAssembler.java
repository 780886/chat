package com.wgq.chat.assemble;

import com.wgq.chat.common.Encrypt;
import com.wgq.chat.pojo.param.PhoneRegisterParam;
import com.wgq.chat.pojo.po.User;
import com.wgq.chat.pojo.param.EmailRegisterParam;

public class UserAssembler {


    public static User assembleUser(EmailRegisterParam emailRegisterParam, Encrypt encrypt) {
        User user = new User();
        user.setEmail(emailRegisterParam.getEmail());
        String encryptPassword = encrypt.encrypt(emailRegisterParam.getPassword());
        user.setPassword(encryptPassword);
        user.setUserName(emailRegisterParam.getUserName());
        user.setChannel(emailRegisterParam.getChannel());
        return user;
    }

    public static User assembleUser(PhoneRegisterParam phoneRegisterParam, Encrypt encrypt) {
        User user = new User();
        user.setMobile(phoneRegisterParam.getPhone());
        String encryptPassword = encrypt.encrypt(phoneRegisterParam.getPassword());
        user.setPassword(encryptPassword);
        user.setUserName(phoneRegisterParam.getUserName());
        user.setChannel(phoneRegisterParam.getChannel());
        return user;
    }


}
