package com.wgq.chat.common;

public interface Encrypt {

    public String encrypt(String text);

    public boolean verify(String unencryptedStr,String encryptStr);

}
