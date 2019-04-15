package com.example.springboot_integration.control;

import com.example.springboot_integration.component.Encryptor;
import com.example.springboot_integration.entity.*;
import com.example.springboot_integration.exception.UnauthorizedException;
import com.example.springboot_integration.repository.AddressRepository;
import com.example.springboot_integration.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private Encryptor encrypt;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService as;
    @PostMapping("/register")
    public Map register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return Map.of("user", user);
    }
    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(userService.getUser(user.getName()))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                })
                .ifPresent(u -> {
                    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                    }
                    Map map = Map.of("uid", u.getId(), "aid", u.getAuthorityId());
                    // 生成加密token
                    String token = encrypt.encrypt(map);
                    // 在header创建自定义的权限
                    response.setHeader("Authorization", token);
                });
    }
    @PostMapping("/users/{uid}/addresses")
    public Map postAddress(@RequestBody Address address, @RequestAttribute int uid) {
        address.setUser(new User(uid));
        as.addAddress(address);
        return Map.of("address", address);
    }

    @GetMapping("/users/{uid}/addresses")
    public Map getAddresses(@RequestAttribute int uid) {
        return Map.of("addresses", as.listAddresses(uid));
    }

    @PatchMapping("/users/{uid}/addresses/{aid}")
    public Map patchAddress(@RequestBody Address address) {
        return Map.of("address", as.updateAddress(address));
    }
}
