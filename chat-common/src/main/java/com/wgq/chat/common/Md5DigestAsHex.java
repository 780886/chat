package com.wgq.chat.common;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Md5DigestAsHex implements Encrypt {

    private static final String SALT = "kite";

    @Override
    public String encrypt(String text) {
        /**
         * 盐值，混淆密码
         */
        return DigestUtils.md5DigestAsHex((SALT + text).getBytes());
    }

    @Override
    public boolean verify(String unencryptedStr, String encryptStr) {
        String encrypt = encrypt(unencryptedStr);
        if (encrypt.equals(encryptStr)){
            return true;
        }
        return false;
    }
}
