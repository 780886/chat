package com.wgq.chat.pojo.po;


import lombok.Data;

import java.util.Date;

@Data
public class User {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * E-MAIL
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 头象
     */
    private String avatar;

    /**
     * 签名
     */
    private String personalSignature;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 最近登录时间
     */
    private Long lastLoginTime;

    /**
     * 是否激活
     */
    private Integer activate;

    /**
     * 激活时间
     */
    private Long activateTime;

    /**
     * 渠道来源
     */
    private String channel;

    /**
     * SECRET MOBILE
     */
    private String secretMobile;

    /**
     * 设备
     */
    private String device;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 设备模型
     */
    private String deviceModel;

    /**
     * ip
     */
    private Long ip;

    /**
     * STATUS
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;
}
