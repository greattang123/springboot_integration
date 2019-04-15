package com.example.springboot_integration.service;

import com.example.springboot_integration.entity.User;
import com.example.springboot_integration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service   //业务逻辑组件
@Slf4j
@Transactional      //事务
public class UserService {
    @Autowired
    private UserRepository us;

    public User addUser(User user) {
        return us.refresh(us.save(user));
    }

    public User getUser(String name){
        return us.findUser(name);
    }
}
