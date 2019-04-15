package com.example.springboot_integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*禁止spring按组件创建对象（组件创建的方法无refresh（）方法
 ，声明refresh（）方法*/
@NoRepositoryBean
/*JpaRepository接口，封装了改变实体对象状态的JPA方法，
 简化了基于实体对象的CRUD操作*/
public interface CustomizedRepository<T,ID> extends JpaRepository<T,ID> {
    T refresh(T t);
}
