package com.baizhi.service;

import com.baizhi.entity.Role;
import com.baizhi.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role queryPermissionByRoleName(String roleName) {
        Role roles = roleMapper.queryPermissionByRoleName(roleName);
        return roles;
    }
}
