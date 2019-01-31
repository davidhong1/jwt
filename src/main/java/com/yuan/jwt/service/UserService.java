package com.yuan.jwt.service;

import com.yuan.jwt.dto.RoleSecurity;
import com.yuan.jwt.exception.CustomException;
import com.yuan.jwt.mapper.RoleMapper;
import com.yuan.jwt.mapper.UserMapper;
import com.yuan.jwt.mapper.UserRoleMapper;
import com.yuan.jwt.model.User;
import com.yuan.jwt.model.UserRole;
import com.yuan.jwt.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description:
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String contact, String password) {
        User user = userMapper.selectByContact(contact);
        String username = user.getUsername();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userSecurityService.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid contact/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user, List<String> roles) {
        if (userMapper.countByUsername(user.getUsername())==0) {
            user.setPw(passwordEncoder.encode(user.getPw()));
            userMapper.insert(user);
            // TODO 批量插入userRole
            for (String item : roles) {
                Long roleId = roleMapper.selectIdByRole(item);
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getId());
                userRoleMapper.insert(userRole);
            }
            List<RoleSecurity> roleSecurities = new ArrayList<>();
            for (String item : roles) {
                RoleSecurity roleSecurity = new RoleSecurity(item);
                roleSecurities.add(roleSecurity);
            }
            return jwtTokenProvider.createToken(user.getUsername(), roleSecurities);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userMapper.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userMapper.selectByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public void signup(String username, String contact, String pw) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        User user = new User();
        user.setUsername(username);
        user.setPw(pw);
        user.setContact(contact);
        signup(user, roles);
    }

}
