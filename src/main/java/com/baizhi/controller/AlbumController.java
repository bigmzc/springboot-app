package com.baizhi.controller;

import com.baizhi.dto.AlbumDto;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
