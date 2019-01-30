package com.yuan.jwt.security;

import com.yuan.jwt.dto.UserSecurity;
import com.yuan.jwt.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description:
 */
@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserSecurity userSecurity = userSecurityService.findByUsername(username);
        if (userSecurity == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(userSecurity.getPw())//
                .authorities(userSecurity.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
