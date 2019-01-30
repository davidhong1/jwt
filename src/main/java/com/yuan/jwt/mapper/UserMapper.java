package com.yuan.jwt.mapper;

import com.yuan.jwt.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

    @Select("select * from user where username=#{username}")
    public User selectByUsername(String username);

}
