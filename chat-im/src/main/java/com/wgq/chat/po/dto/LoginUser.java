package com.wgq.chat.po.dto;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
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
}
