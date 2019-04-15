package com.example.springboot_integration.repository.Implement;

import com.example.springboot_integration.repository.CustomizedRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * 自定义接口实现
 * @param <T>
 * @param <ID>
 */
public class CustomizedRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements CustomizedRepository<T,ID> {
    private EntityManager em;
    public CustomizedRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em=entityManager;
    }

    @Override
    public T refresh(T t) {
        em.refresh(t);
        return t;
      //  return (em.refresh(t));  //refresh() 返回值为空
    }
}
