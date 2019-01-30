package com.yuan.jwt.service;

import com.yuan.jwt.dto.RoleSecurity;
import com.yuan.jwt.dto.UserSecurity;
import com.yuan.jwt.mapper.RoleMapper;
import com.yuan.jwt.mapper.UserMapper;
import com.yuan.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description:
 */
@Service
public class UserSecurityService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    public UserSecurity findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        UserSecurity userSecurity = new UserSecurity(user);
        List<String> rolesStr = roleMapper.selectRolesByUserId(userSecurity.getId());
        List<RoleSecurity> roles = new ArrayList<>();
        for (String item: rolesStr) {
            RoleSecurity roleSecurity = new RoleSecurity(item);
            roles.add(roleSecurity);
        }
        userSecurity.setRoles(roles);
        return userSecurity;
    }

}
