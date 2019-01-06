package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    Admin queryOneAdminByUsername(String username);

    Admin queryAdminRolesByUserName(String username);
}
