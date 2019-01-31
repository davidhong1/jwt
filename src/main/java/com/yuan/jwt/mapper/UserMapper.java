package com.yuan.jwt.mapper;

import com.yuan.jwt.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

    @Select("select * from user where username=#{username}")
    public User selectByUsername(String username);

    @Select("select * from user where contact=#{contact}")
    public User selectByContact(String contact);

    @Select("select count(*) from user where username=#{username}")
    public Integer countByUsername(String username);

    @Delete("delete from user where username = #{username}")
    public void deleteByUsername(String username);

}
