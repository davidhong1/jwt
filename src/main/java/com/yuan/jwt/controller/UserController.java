package com.yuan.jwt.controller;

import com.yuan.jwt.dto.BRB;
import com.yuan.jwt.dto.BRStatus;
import com.yuan.jwt.exception.CustomException;
import com.yuan.jwt.model.User;
import com.yuan.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description:
 */
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public BRB login(@RequestBody Map<String, String> req) {
        String contact = (String)req.get("contact");
        String password = (String)req.get("password");
        return new BRB(BRStatus.SUCCESS, userService.signin(contact, password));
    }

    @PostMapping("/signup")
    public BRB signup(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        String pw = req.get("pw");
        String contact = req.get("contact");
        if (username==null || pw==null || contact==null)
            throw new CustomException("参数错误", HttpStatus.BAD_REQUEST);
        userService.signup(username, contact, pw);
        return new BRB(BRStatus.SUCCESS);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User search(@PathVariable String username) {
        return userService.search(username);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public User whoami(HttpServletRequest req) {
        return userService.whoami(req);
    }

}
