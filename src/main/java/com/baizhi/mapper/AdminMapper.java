package com.baizhi.mapper;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AdminMapper extends Mapper<Admin> {
    Admin queryRolesByUserName(@Param("username") String username);
}
