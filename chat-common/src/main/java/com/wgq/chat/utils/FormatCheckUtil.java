package com.wgq.chat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatCheckUtil {


    private static final int MIN_PASSWORD_LENGTH = 6; // 密码最小长度
    private static final int MAX_PASSWORD_LENGTH = 18; // 密码最大长度


    private static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$"); // 手机号格式正则表达式
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]+$"); // 用户名格式正则表达式

    /**
     * 检查密码是否合法：6~18位数字或字母
     *
     * @param password 密码
     * @return 是否合法
     */
    public static boolean checkPassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查邮箱格式是否合法
     *
     * @param email 邮箱地址
     * @return 是否合法
     */
    public static boolean checkEmail(String email) {
        // 正则表达式，表示匹配电子邮件地址格式的正则表达式
        String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 检查手机号格式是否合法：11位数字，以1开头
     *
     * @param mobile 手机号
     * @return 是否合法
     */
    public static boolean checkMobile(String mobile) {
        return mobile != null && MOBILE_PATTERN.matcher(mobile).matches();
    }

    /**
     * 检查用户名格式是否合法：只能包含字母、数字、下划线
     *
     * @param username 用户名
     * @return 是否合法
     */
    public static boolean checkUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }


}

