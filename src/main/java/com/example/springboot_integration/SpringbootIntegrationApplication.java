package com.example.springboot_integration;

import com.example.springboot_integration.repository.Implement.CustomizedRepositoryImpl;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
//修改入口配置，声明自定义实现
@EnableJpaRepositories(repositoryBaseClass = CustomizedRepositoryImpl.class)
public class SpringbootIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootIntegrationApplication.class, args);
    }
    /*需添加 <groupId>org.springframework.security</groupId>
               <artifactId>spring-security-crypto</artifactId> 依赖*/
    //直接注入编码组件
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected Hibernate5Module module() {
        Hibernate5Module module = new Hibernate5Module();
        // 序列化延迟加载对象的ID
        module.enable(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        return module;
    }
}
