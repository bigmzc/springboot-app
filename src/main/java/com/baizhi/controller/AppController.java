package com.baizhi.controller;


import com.baizhi.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainpage")
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping("/query")
    @ResponseBody
    public Object queryMainpageData(String uid, String type, String sub_type) {
        Object result = appService.queryFirstPage(uid, type, sub_type);
        return result;
    }
}
