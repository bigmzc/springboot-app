package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class POIController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("/export")
    public void fileExport(HttpServletResponse response) {
        List<Album> albumAndAudio = albumService.getAllAlbumAndAudio();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑详情", "音频"),
                Album.class, albumAndAudio);
        String backName = "专辑详情.xls";
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(backName, "UTF-8"));
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
