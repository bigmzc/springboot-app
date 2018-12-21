package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/uploadFile")
    @ResponseBody
    public void addOneAudioFile(MultipartFile file, Chapter chapter) {

        String fileName = file.getOriginalFilename();
        String path = "F:/source/springboot-app/src/main/webapp/audio/audios";
        File dest = new File(path + "/" + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取文件大小
        long size = file.getSize();
        System.out.println(size);
        double mbSize = size / 1048576;
        System.out.println(mbSize);
        String mbSize2 = mbSize + "" + "M";

        //获取文件时长
        Encoder encoder = new Encoder();
        MultimediaInfo info = null;
        try {
            info = encoder.getInfo(dest);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        long ls = info.getDuration();
        String duration = ls / 60000 + "分" + ((ls % 60000) / 1000) + "秒";

        //准备url
        String filePath = "/audio/audiopic/" + fileName;

        //准备UUID作为主键
        String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        chapter.setId(substring);
        chapter.setSize(mbSize2);
        chapter.setDuration(duration);
        chapter.setUrl(filePath);
        chapterService.insertMultiAudio(chapter);
    }
}
