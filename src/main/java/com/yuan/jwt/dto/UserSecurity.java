package com.yuan.jwt.dto;

import com.yuan.jwt.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description:
 */
public class UserSecurity {

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String pw;

    @Setter
    @Getter
    private List<RoleSecurity> roles;

    public UserSecurity(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.pw = user.getPw();
    }

}
