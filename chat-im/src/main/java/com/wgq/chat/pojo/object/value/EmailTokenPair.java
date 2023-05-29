package com.wgq.chat.pojo.object.value;


import com.wgq.chat.utils.StringUtils;

public class EmailTokenPair {
    private String email;
    private String token;

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
    public static EmailTokenPair create(String email, String token) {
        return new EmailTokenPair(email,token);
    }

    private EmailTokenPair(String email, String token) {
        this.email = email;
        this.token = token;
    }

    @Override public String toString() {
        return this.email + "|" + this.token;
    }

    public static EmailTokenPair parse(String emailToken) {
        if (StringUtils.isNullOrEmpty(emailToken)) {
            return null;
        }
        String[] pair = emailToken.split("\\|");

        return new EmailTokenPair(pair[0], pair[1]);
    }
}
