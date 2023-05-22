package com.wgq.chat.assemble;

import com.wgq.chat.common.Encrypt;
import com.wgq.chat.common.Md5DigestAsHex;
import com.wgq.chat.po.entity.User;
import com.wgq.chat.po.param.EmailRegisterParam;
import org.springframework.beans.factory.annotation.Autowired;

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


}
