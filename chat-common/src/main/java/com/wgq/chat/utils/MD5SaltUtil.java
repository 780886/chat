package com.wgq.chat.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MD5SaltUtil {

    private static final int SALT_LENGTH = 16;

    /**
     * 生成指定长度的随机盐值
     *
     * @param length    盐值长度
     * @return          盐值字符串
     */
    public static String generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[length];
        random.nextBytes(saltBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * 对字符串进行 MD5 加密，并使用随机盐值增加安全性
     *
     * @param str       需要加密的字符串
     * @return          加密后的字符串
     */
    public static String md5WithSalt(String str) {
        String result = null;
        try {
            // 生成随机盐值
            String salt = generateSalt(SALT_LENGTH);

            // 将盐值添加到待加密字符串前面
            String saltedStr = salt + str;

            // 创建 MessageDigest 实例
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            // 计算哈希值并转换为 16 进制字符串
            byte[] hash = messageDigest.digest(saltedStr.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            // 将盐值和哈希后的密码拼接返回
            result = salt + sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 验证字符串和指定的 MD5 加密结果是否匹配
     *
     * @param str       待验证的字符串
     * @param md5       MD5 加密结果（包含盐值）
     * @return          true 表示匹配，false 表示不匹配
     */
    public static boolean verify(String str, String md5) {
        boolean result = false;
        try {
            // 从 MD5 字符串中截取盐值
            String salt = md5.substring(0, SALT_LENGTH * 2);

            // 将盐值添加到待验证字符串前面
            String saltedStr = salt + str;

            // 创建 MessageDigest 实例
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            // 计算哈希值并转换为 16 进制字符串
            byte[] hash = messageDigest.digest(saltedStr.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            // 判断哈希结果是否和 MD5 字符串中的哈希结果相同
            String hashedStr = salt + sb.toString();
            result = hashedStr.equals(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}