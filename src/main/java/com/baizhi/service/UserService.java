package com.baizhi.service;

import com.baizhi.dto.UserDto;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto queryActiveUsers();
    Map<String, List<Province>> queryUsersDistrubution();
    Map<String, List<Province>> queryUsersDistrubutionBySex(String sex);

    //user login
    User queryUserByPhone(String phone);

    User queryUserById(Integer id);

    //user regist
    Object userRegister(String phone, String password);

    User updateUserInfo(User user);
}
