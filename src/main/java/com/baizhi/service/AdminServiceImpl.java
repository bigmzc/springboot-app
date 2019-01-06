package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Admin queryOneAdminByUsername(String username) {
        Admin admin = new Admin(null, username, null, null);
        Admin res = adminMapper.selectOne(admin);
        return res;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Admin queryAdminRolesByUserName(String username) {
        Admin admin = adminMapper.queryRolesByUserName(username);
        return admin;
    }
}
