package com.baizhi.service;

import com.baizhi.dto.UserDto;
import com.baizhi.entity.Error;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User queryUserByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        User user1 = userMapper.selectOne(user);
        return user1;
    }

    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setId(id);
        User user1 = userMapper.selectOne(user);
        return user1;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object userRegister(String phone, String password) {
        //注册前查询该手机号是否存在
        User user = new User();
        user.setPhone(phone);
        User user1 = userMapper.selectOne(user);
        System.out.println(user1);
        if (user1 != null) {
            return new Error("-200", "手机号已经存在");
        } else {
            //MD5,salt
            String salt = Md5Util.getSalt();
            String encryption = Md5Util.encryption(password + salt);

            user.setSalt(salt);
            user.setPassword(encryption);
            user.setRegDate(new Date());
            userMapper.insert(user);

            System.out.println("---------------------------------------------------");
            Map<String, String> map = new HashMap<>();
            User user3 = new User();
            user3.setPhone(phone);
            User user2 = userMapper.selectOne(user3);
            String password1 = user2.getPassword();
            Integer id = user2.getId();
            map.put("password", password1);
            map.put("uid", Integer.toString(id));
            map.put("phone", phone);
            return map;
        }

    }

    @Override
    public User updateUserInfo(User user) {
        String password = user.getPassword();
        String salt = Md5Util.getSalt();
        String encryption = Md5Util.encryption(password + salt);
        user.setSalt(salt);
        user.setPassword(encryption);
        userMapper.updateByPrimaryKey(user);
        User user1 = new User();
        user1.setId(user.getId());
        User user2 = userMapper.selectOne(user1);
        return user2;
    }
}
