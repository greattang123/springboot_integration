package com.example.springboot_integration.interceptor;

import com.example.springboot_integration.component.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private Encryptor ec;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从header获取自定义属性
        return Optional.ofNullable(request.getHeader("Authorization"))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录！");
                })
                .map(token -> {
                    var map = ec.decrypt(token);
                    request.setAttribute("uid", Integer.valueOf(map.get("uid").toString()));
                    request.setAttribute("aid", Integer.valueOf(map.get("aid").toString()));
                    return true;
                })
                // header不存在Authorization
                .orElse(false);
    }
}
