package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
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
        System.out.println(admin);
        Admin adminInDb = adminService.queryOneAdminByUsername(admin.getUsername());
        if (adminInDb == null) {
            return "redirect:/login.jsp";
        } else {
            if (admin.getPassword().equals(adminInDb.getPassword())) {
                String validCode = (String) session.getAttribute("vcode");
                if (vcode.toLowerCase().equals(validCode)) {
                    //三次校验正确
                    session.setAttribute("username", adminInDb.getUsername());
                    return "redirect:/main/main.jsp";
                } else {
                    return "redirect:/login.jsp";
                }
            } else {
                return "redirect:/login.jsp";
            }
        }
    }
}
