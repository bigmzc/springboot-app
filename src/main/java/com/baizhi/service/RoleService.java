package com.baizhi.service;

import com.baizhi.entity.Role;

public interface RoleService {
    Role queryPermissionByRoleName(String roleName);
}
