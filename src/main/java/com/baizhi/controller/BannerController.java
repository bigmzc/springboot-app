package com.baizhi.controller;

import com.baizhi.dto.BannerDto;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryBannerByPage")
    @ResponseBody
    public BannerDto queryBannerByPage(int page, int rows) {
        BannerDto bannerDto = bannerService.queryBannerByPage(page, rows);
        return bannerDto;
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public void updateBannerStatus(Banner banner) {
        System.out.println(banner);
        bannerService.updateBannerStatus(banner);
    }
}
