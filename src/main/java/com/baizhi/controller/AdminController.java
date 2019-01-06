package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/loginController")
    public String AdminLoginController(Admin admin, String vcode, HttpSession session) {
        Admin adminInDb = adminService.queryOneAdminByUsername(admin.getUsername());
        if (adminInDb == null) {
            return "redirect:/login.jsp";
        } else {
            if (admin.getPassword().equals(adminInDb.getPassword())) {
                String validCode = (String) session.getAttribute("vcode");
                if (vcode.toLowerCase().equals(validCode)) {
                    //三次校验正确
                    session.setAttribute("username", adminInDb.getUsername());
                    return "redirect:/main/main2.jsp";
                } else {
                    return "redirect:/login.jsp";
                }
            } else {
                return "redirect:/login.jsp";
            }
        }
    }

    @RequestMapping("/login")
    public String adminLogin(Admin admin, String vcode, HttpSession session) {
        System.out.println(admin);
        if (admin.getUsername() == null) {
            return "redirect:/login.jsp";
        } else {
            String validCode = (String) session.getAttribute("vcode");
            if (vcode.toLowerCase().equals(validCode)) {
                //如果验证码正确，开始认证主体
                Subject subject = SecurityUtils.getSubject();
                AuthenticationToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
                try {
                    subject.login(token);
                    return "redirect:/main/main2.jsp";
                } catch (UnknownAccountException e) {
                    System.out.println("账户不存在");
                    return "redirect:/login.jsp";
                } catch (IncorrectCredentialsException e) {
                    System.out.println("密码错误");
                    return "redirect:/login.jsp";
                }
                //TODO adminService ???? using
            } else {
                System.out.println("验证码错误");
                return "redirect:/login.jsp";
            }

        }
    }
}
