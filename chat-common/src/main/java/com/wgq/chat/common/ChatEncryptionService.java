package com.wgq.chat.common;

import com.wgq.chat.common.cryptogram.Base64;
import com.wgq.chat.common.cryptogram.MessageSignature;
import com.wgq.chat.common.cryptogram.ThreeDES;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class ChatEncryptionService implements EncryptionService {

    private static final String SALT = "kite";

    @Override
    public String encryptPassword(String password) {
        return MessageSignature.getInstance().md5(password);
    }

    @Override
    public String base64Encode(String originCode) {
        return Base64.encodeBytes(originCode.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String base64Decode(String base64) {
        try {
            return new String(Base64.decode(base64), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String generateToken(String originCode, String originPassword) {
        return ThreeDES.getInstance().encrypt(originPassword, originCode);
    }

    @Override
    public String decryptToken(String token, String originPassword) {
        return ThreeDES.getInstance().decrypt(originPassword, token);
    }

    @Override
    public boolean verify(String unencryptedStr, String encryptStr) {
        String encrypt = encryptPassword(unencryptedStr);
        if (encrypt.equals(encryptStr)){
            return true;
        }
        return false;
    }
}
