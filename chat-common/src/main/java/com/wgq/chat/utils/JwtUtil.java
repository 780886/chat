package com.wgq.chat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    // 签名密钥
    private static final String SECRET_KEY = "ewafda";

    // 过期时间（30分钟）
    private static final long EXPIRATION_TIME = 30 * 60 * 1000;

    /**
     * 生成 Token
     *
     * @param subject  用户名、用户ID等信息
     * @return         JWT Token
     */
    public static String generateToken(String subject) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 解析 Token
     *
     * @param token    JWT Token
     * @return         用户名、用户ID等信息
     */
    public static String parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}