package com.yuan.jwt.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description:
 */
public class RoleSecurity implements GrantedAuthority {

    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }

    public RoleSecurity() {}

    public RoleSecurity(String role) {
        this.role = role;
    }

}
