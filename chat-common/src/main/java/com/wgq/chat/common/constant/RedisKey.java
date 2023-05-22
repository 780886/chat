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

    // 用户登录 token 前缀
    public static final String USER_TOKEN_PREFIX = "user:token:";
    /**
     * 用户token存放
     */
    public static final String USER_TOKEN_STRING = "userToken:uid_%d";

    // 短信验证码前缀
    public static final String LOGIN_PHONE_PREFIX = "login:phone:";

    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }

}
