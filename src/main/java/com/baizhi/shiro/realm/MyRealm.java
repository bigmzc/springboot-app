package com.baizhi.shiro.realm;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        Admin admin = new Admin();
        admin.setUsername(principal);
        Admin admin1 = adminMapper.selectOne(admin);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, admin1.getPassword(), ByteSource.Util.bytes("salt"), this.getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        Admin admin = new Admin();
        admin.setUsername(primaryPrincipal);
        Admin admin1 = adminMapper.selectOne(admin);
        if (primaryPrincipal.equals(admin1.getUsername())) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRole("super");
            ArrayList<String> list = new ArrayList<>();
            authorizationInfo.addRoles(list);
            authorizationInfo.addStringPermission("user:delete");
            authorizationInfo.addStringPermissions(Arrays.asList("admin:delete", "admin:add"));
            return authorizationInfo;
        }
        return null;
    }
}
