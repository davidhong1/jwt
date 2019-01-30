package com.yuan.jwt.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author: mac
 * @date: 2019-01-30
 * @description:
 */
public class Role {

    @Id
    @GeneratedValue(generator = "JDBC") // 插入id，回显id
    @Setter @Getter private Long id;
    @Setter @Getter private String role;

}

