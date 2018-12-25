package com.baizhi.service;

import com.baizhi.dto.UserDto;
import com.baizhi.entity.Province;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto queryActiveUsers();

    Map<String, List<Province>> queryUsersDistrubution();

    Map<String, List<Province>> queryUsersDistrubutionBySex(String sex);
}
