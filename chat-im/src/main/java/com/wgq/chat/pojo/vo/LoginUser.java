package com.wgq.chat.pojo.vo;

import java.util.LinkedHashMap;
import java.util.Map;


public class LoginUser {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头象
     */
    private String avatar;
    /**
     * 设备id
     */
    private String deviceId;

    private Integer days;

    private Long expireAt;

    private Map<String, Object> extensions = new LinkedHashMap<>();

    public static class LoginUserBuild{
       LoginUser loginUser  =  new LoginUser();

       public LoginUserBuild userId(Long userId){
           loginUser.userId = userId;
           return this;
       }

        public LoginUserBuild nickName(String nickName){
            loginUser.nickName = nickName;
            return this;
        }

        public LoginUserBuild userName(String userName){
            loginUser.userName = userName;
            return this;
        }

        public LoginUserBuild avatar(String avatar){
            loginUser.avatar = avatar;
            return this;
        }

        public LoginUserBuild deviceId(String deviceId){
            loginUser.deviceId = deviceId;
            return this;
        }

        public LoginUserBuild days(Integer days){
            loginUser.days = days;
            return this;
        }

        public LoginUserBuild expireAt(Long expireAt){
            loginUser.expireAt = expireAt;
            return this;
        }

        public LoginUserBuild extensions(Map<String, Object> extensions){
            loginUser.extensions = extensions;
            return this;
        }

        public LoginUser build(){
           return loginUser;
        }

    }

    public Long getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Integer getDays() {
        return days;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
