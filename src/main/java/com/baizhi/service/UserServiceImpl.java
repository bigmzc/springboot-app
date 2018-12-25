package com.baizhi.service;

import com.baizhi.dto.UserDto;
import com.baizhi.entity.Province;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDto queryActiveUsers() {

        UserDto userDto = new UserDto();
        List<Integer> list = new ArrayList<>();
        int[] timeLength = {7, 15, 30, 90, 180, 365};
        for (int i : timeLength) {
            Integer integer = userMapper.queryActiversUser(i);
            list.add(integer);
        }
        userDto.setData(list);
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, List<Province>> queryUsersDistrubution() {
        List<Province> provinces = userMapper.queryUsersDistrubution();
        Map<String, List<Province>> map = new HashMap<>();
        map.put("data", provinces);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, List<Province>> queryUsersDistrubutionBySex(String sex) {
        List<Province> res = userMapper.queryUsersDistrubutionBySex(sex);
        Map<String, List<Province>> map = new HashMap<>();
        map.put("data", res);
        return map;
    }
}
