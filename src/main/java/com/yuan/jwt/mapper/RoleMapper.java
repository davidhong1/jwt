package com.yuan.jwt.mapper;

import com.yuan.jwt.model.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleMapper extends Mapper<Role> {
}
