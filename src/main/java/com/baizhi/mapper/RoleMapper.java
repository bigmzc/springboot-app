package com.baizhi.mapper;

import com.baizhi.entity.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {
    Role queryPermissionByRoleName(@Param("roleName") String roleName);
}
