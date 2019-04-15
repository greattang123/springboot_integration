package com.example.springboot_integration.repository;

import com.example.springboot_integration.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomizedRepository<User,Integer>{
    //@Query声明JPQL查询语句
    @Query("SELECT u FROM User u WHERE u.name=:name")
    //@Param声明方法参数对应的JPQL查询语句
    User findUser(@Param("name")String name);
}
