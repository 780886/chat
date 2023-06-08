package com.wgq.chat.interceptor;

import com.wgq.chat.service.LoginService;
import com.wgq.chat.utils.JwtUtil;
import com.wgq.chat.utils.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;


@Order(-2)
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_SCHEMA = "Bearer ";
    public static final String ATTRIBUTE_UID = "uid";

    @Resource
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        String aud = loginService.getValidSubject(token);
        if (StringUtils.isNullOrEmpty(aud)){
            request.setAttribute(ATTRIBUTE_UID,aud);
            return true;
        }else {
            return false;
        }


    }

}
