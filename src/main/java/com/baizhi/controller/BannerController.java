package com.baizhi.controller;

import com.baizhi.dto.BannerDto;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
        //System.out.println(banner);
        bannerService.updateBannerStatus(banner);
    }

    @RequestMapping("/fileupload")
    @ResponseBody
    public void addOneBanner(MultipartFile file, Banner banner) {
        String fileName = file.getOriginalFilename();
        String imgPath = "/img/test/" + fileName;
        banner.setImgPath(imgPath);
        bannerService.insertOneBanner(banner);
        int size = (int) file.getSize();
        System.out.println(fileName + "----" + size);

        String path = "F:/source/springboot-app/src/main/webapp/img/test";
        File dest = new File(path + "/" + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
