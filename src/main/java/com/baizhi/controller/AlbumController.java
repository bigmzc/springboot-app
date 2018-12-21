package com.baizhi.controller;

import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/queryByPage")
    @ResponseBody
    public AlbumDto queryAllAlbum(int page, int rows) {
        AlbumDto albumDto = albumService.queryAllAlbum(page, rows);
        //System.out.println(albumDto);
        return albumDto;
    }

    @RequestMapping("/addOneAlbum")
    @ResponseBody
    public void addOneAlbum(MultipartFile file, Album album) {
        String fileName = file.getOriginalFilename();
        String imgPath = "/audio/audiopic/" + fileName;

        album.setCoverImg(imgPath);
        album.setCount(0);
        albumService.insertOneAlbum(album);

        String path = "F:/source/springboot-app/src/main/webapp/audio/audiopic";
        File dest = new File(path + "/" + fileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/queryOneById")
    @ResponseBody
    public Album queryOneById(Integer id) {
        Album album = albumService.queryOneAlbumById(id);
        return album;
    }
}
