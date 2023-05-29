package com.wgq.chat.common;

public interface EncryptionService {

    String encryptPassword(String password);

    String base64Encode(String originCode);

    String base64Decode(String base64);

    String generateToken(String originCode, String originPassword);

    String decryptToken(String token, String originPassword);

    public boolean verify(String unencryptedStr,String encryptStr);

}
