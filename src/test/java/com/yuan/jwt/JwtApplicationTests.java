package com.yuan.jwt;

import com.yuan.jwt.mapper.UserMapper;
import com.yuan.jwt.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        long obe = 1l;
        User user = userMapper.selectByPrimaryKey(obe);
        if (user==null) {
            log.info("user=null");
        } else {
            log.info(user.getUsername());
        }
    }

}

