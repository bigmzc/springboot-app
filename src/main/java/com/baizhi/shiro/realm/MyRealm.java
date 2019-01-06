package com.baizhi.shiro.realm;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Permission;
import com.baizhi.entity.Role;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import com.baizhi.service.RoleService;
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
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

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

        //测试Roles查询
        Admin admin2 = adminService.queryAdminRolesByUserName(primaryPrincipal);
        List<Role> roles = admin2.getRoles();
        List<String> allRoles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();

        for (Role role : roles) {
            String rolename = role.getRolename();
            allRoles.add(rolename);
            //通过roles查询permission
            Role role1ContainPermissions = roleService.queryPermissionByRoleName(rolename);
            List<Permission> permissions1 = role1ContainPermissions.getPermissions();
            for (Permission permission : permissions1) {
                String permissionname = permission.getPermissionname();
                permissions.add(permissionname);
            }
        }

        Admin admin = new Admin();
        admin.setUsername(primaryPrincipal);
        Admin admin1 = adminMapper.selectOne(admin);

        if (primaryPrincipal.equals(admin1.getUsername())) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRoles(allRoles);
            //authorizationInfo.addStringPermission("user:delete");
            //authorizationInfo.addStringPermissions(Arrays.asList("admin:delete", "admin:add"));
            authorizationInfo.addStringPermissions(permissions);
            return authorizationInfo;
        }
        return null;
    }
}
