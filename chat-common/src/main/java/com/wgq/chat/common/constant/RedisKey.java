package com.wgq.chat.common.constant;

/**
 * @author zhongzb create on 2021/06/10
 */
public class RedisKey {


    private static final String BASE_KEY = "chat:";
    /**
     * 用户信息
     */
    public static final String USER_INFO_STRING = "userInfo:uid_%d";
    /**
     * 用户token存放
     */
    public static final String USER_TOKEN_PREFIX = "userToken:uid_%d";


    public static final String SMS_CODE_CACHE_PREFIX = "sms:code:";

    public static String getKey(String key, Object... objects) {
        String format = String.format(key, objects);
        return BASE_KEY + format;
    }

    public static void main(String[] args) {
        String key = getKey(SMS_CODE_CACHE_PREFIX + 1, "%s");
        System.out.println(key);
    }

}
