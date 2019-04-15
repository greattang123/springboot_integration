package com.example.springboot_integration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id  //主键标识
    //主键生成策略------自增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    //禁止password序列化
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    //用户权限为1（最高）
    private int authorityId = 1;
    @Column(columnDefinition = "TIMESTAMP NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "DATETIME NOT NULL " +  // DEFAULT DATETIME
            "DEFAULT CURRENT_TIMESTAMP ON UPDATE " +   //ON UPDATE
            "CURRENT_TIMESTAMP",   //DON NOT FORGET CURRENT_TIMESTAMP
            updatable = false, insertable = false)
    private LocalDateTime updateTime;

    public User(int id) {
        this.id=id;  //user_id 为Address表中关系维护的唯一参照
    }
}
