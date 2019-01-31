package com.yuan.jwt.mapper;

import com.yuan.jwt.model.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleMapper extends Mapper<Role> {

    @Select("select id from role where role = #{role}")
    Long selectIdByRole(String role);

    /**
     * 通过用户ID获取用户角色列表
     * @param userId
     * @return
     */
    @Select("select role.role from user_role, role where user_role.user_id = #{userId} and user_role.role_id = role.id")
    List<String> selectRolesByUserId(Long userId);

}
