package com.wgq.chat.utils;

import java.util.Random;

public class VerificationCodeUtil {
    // 指定验证码为 4 位数字
    private static final int CODE_LENGTH = 6;

    // 生成随机验证码
    public static String generateVerificationCode() {
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++) {
            codeBuilder.append(random.nextInt(10));
        }
        return codeBuilder.toString();
    }
}
