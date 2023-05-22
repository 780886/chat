package com.wgq.chat.utils;

public class StringUtils {

    /**
     * null或""为true 否则为false
     */
    public static boolean isNullOrEmpty(Object str) {
        return str == null || "".equals(str.toString().trim());
    }

}
