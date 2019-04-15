package com.example.springboot_integration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {
    public static final String UNAUTHORIZED = "未登录";
    public static final String COOKIE_PARES_ERROR = "Cookie解析错误";
    public static final String LOGIN_ERROR = "用户名密码错误";

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, UNAUTHORIZED);
    }

    public UnauthorizedException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

}
