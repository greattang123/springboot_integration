package com.example.springboot_integration.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Component
public class ObjectMappe {
    @Autowired
    private ObjectMapper mapper;
    //将map对象序列化为json字符串
    public String writeValueAsString(Map payload) {
        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    //将json字符串反序列化为指定Map对象
    public Map<String, Object> readValue(String json) {
        try {
            return mapper.readValue(json, Map.class);
        } catch (IOException e) {
           e.printStackTrace();
             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录！");
        }

    }
}
