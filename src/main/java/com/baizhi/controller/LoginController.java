package com.baizhi.controller;

import com.baizhi.entity.Error;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Object userLogin(String phone, String password, String code) {
        System.out.println(phone + "----" + password + "------" + code);
        if (password == null && code == null) {
            return new Error("-200", "密码错误");
        } else {
            if (code == null) {
                //校验password
                User user = userService.queryUserByPhone(phone);
                String password1 = user.getPassword();

                String salt = user.getSalt();
                String afterSalt = Md5Util.encryption(password + salt);
                if (password1.equals(afterSalt)) {
                    return user;
                } else {
                    return new Error("-200", "密码错误");
                }
            } else {
                //校验code,code存在于redis 并设置失效时间
                return new Error("-200", "密码错误");
            }
        }
    }

    @RequestMapping("/regist")
    @ResponseBody
    public Object userRegist(String phone, String password) {
        System.out.println(phone + "----" + password);
        Object res = userService.userRegister(phone, password);
        return res;
    }

    @RequestMapping("/modify")
    @ResponseBody
    public Object updateUserInfo(User user) {
        User user2 = userService.queryUserById(user.getId());
        User user1 = userService.updateUserInfo(user);
        if (!user2.equals(user1)) {
            return user1;
        } else {
            return new Error("-200", "手机号已经存在");
        }
    }
}

