package com.yuan.jwt.controller;

import com.yuan.jwt.dto.BRB;
import com.yuan.jwt.dto.BRStatus;
import com.yuan.jwt.mapper.UserMapper;
import com.yuan.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: mac
 * @date: 2019-01-30
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    public BRB get(@PathVariable Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return new BRB(BRStatus.SUCCESS, user);
    }

    @PostMapping
    public BRB insert(@RequestBody User user) {
        userMapper.insert(user);
        return new BRB(BRStatus.SUCCESS);
    }

}
