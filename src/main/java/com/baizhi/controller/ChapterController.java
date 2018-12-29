package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //音频上传
    @RequestMapping("/uploadFile")
    @ResponseBody
    public void addOneAudioFile(MultipartFile file, Chapter chapter) {

        String fileName = file.getOriginalFilename();
        String path = "F:/source/springboot-app/src/main/webapp/audio/audios";
        //String path = "F:/";
        File dest = new File(path + "/" + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取文件大小
        long size = file.getSize();
        double mbSize = size / (1024 * 1024.0);
        double value = new BigDecimal(mbSize).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String mbSize2 = value + "" + "M";

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

    //文件下载
    @RequestMapping("/download")
    @ResponseBody
    public void fileDownload(HttpServletResponse response, String fileName)
            throws UnsupportedEncodingException {
        String[] strings = fileName.split("/");

        String realFileName = strings[3];
        System.out.println(realFileName);

        String filePath = "F:/source/springboot-app/src/main/webapp";
        File file = new File(filePath + fileName);
        if (file.exists()) {
            System.out.println("file----------exits");
            response.setContentType("application/force-download");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realFileName, "UTf-8"));
            byte[] buffer = new byte[1024];

            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;

            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int read = bis.read(buffer);
                while (read != -1) {
                    os.write(buffer);
                    read = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //文件下载2
    @RequestMapping("/download2")
    public void fileDownload2(HttpSession session, HttpServletResponse response, String fileName, String title) {
        String[] strings = fileName.split("/");
        String realFileName = strings[3];
        String realPath = session.getServletContext().getRealPath("/audio/audios");
        byte[] bs = new byte[0];
        try {
            bs = FileUtils.readFileToByteArray(new File(realPath + "/" + realFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String extension = FilenameUtils.getExtension(fileName);
        String backName = title + "." + extension;
        //设置响应头信息：以附件形式下载
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(backName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("audio/mpeg");
        try {
            ServletOutputStream out = response.getOutputStream();
            out.write(bs);
            if (out != null) {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
