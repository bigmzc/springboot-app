package com.baizhi.controller;

import com.baizhi.dto.UserDto;
import com.baizhi.entity.Province;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/activeUser")
    @ResponseBody
    public UserDto activeUser() {
        UserDto userDto = userService.queryActiveUsers();
        //String jsonString = JSONObject.toJSONString(userDto);
        //GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-844b432942ef47f4bad2483455ff6945");
        //System.out.println(jsonString);
        //goEasy.publish("userana",jsonString);
        return userDto;
    }


    @RequestMapping("/distribution")
    @ResponseBody
    public Map<String, List<Province>> getUsersDistribution() {
        Map<String, List<Province>> listMap = userService.queryUsersDistrubution();
        return listMap;
    }

    @RequestMapping("/distribution2")
    @ResponseBody
    public Map<String, List<Province>> getUsersDistribution2() {
        Map<String, List<Province>> map = userService.queryUsersDistrubutionBySex("男");
        return map;
    }

    @RequestMapping("/distribution3")
    @ResponseBody
    public Map<String, List<Province>> getUsersDistribution3() {
        Map<String, List<Province>> listMap = userService.queryUsersDistrubutionBySex("女");
        return listMap;
    }

}
