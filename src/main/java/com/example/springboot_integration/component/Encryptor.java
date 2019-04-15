package com.example.springboot_integration.component;

import com.example.springboot_integration.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

import java.util.Map;
@Slf4j
@Component
public class Encryptor {
    @Value("${my.secret_key}")
    private String secretKey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private ObjectMappe omc;
    // 加密
    public String encrypt(Map payload) {
        String json = omc.writeValueAsString(payload);
        /* Encryptors.text(secretKey, salt)，基于指定密钥和盐值创建文本加密器,
            encrypt()加密，decrypt()解密*/
        log.debug(json);
        return Encryptors.text(secretKey, salt).encrypt(json);
    }
    // 解密
    public Map<String, Object> decrypt(String encryptString) {
        try {
            String json = Encryptors.text(secretKey, salt).decrypt(encryptString);
            return omc.readValue(json);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }
}
