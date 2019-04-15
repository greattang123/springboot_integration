package com.example.springboot_integration.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id  //主键标识
    //主键生成策略------自增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    private String comment;
    //延迟加载
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(columnDefinition = "TIMESTAMP NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime insertTime;

    @Column(columnDefinition = "DATETIME NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP ON UPDATE " +
            "CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime updateTime;

    public Address(String detail) {
        this.detail = detail;
    }
}
