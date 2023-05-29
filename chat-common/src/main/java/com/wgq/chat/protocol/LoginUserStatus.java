package com.wgq.chat.protocol;

public class LoginUserStatus {

    public static final Integer STATUS_NORMAL = 1;
    public static final Integer STATUS_FREEZE = 2;
    public static final Integer STATUS_DISABLE = 0;
    private Integer status;
    private Long expireAt;

    public LoginUserStatus(Integer status, Long expireAt) {
        this.status = status;
        this.expireAt = expireAt;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getExpireAt() {
        return this.expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }
}
