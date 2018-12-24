package com.baizhi.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseEnvTest {


    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private AlbumMapper albumMapper;

    @Test
    public void test1() {

        int count = bannerMapper.selectCount(new Banner());
        System.out.println(count);
    }

    @Test
    public void test2() {
        Banner banner = new Banner();
        banner.setId(9);
        banner.setStatus("Y");
        bannerService.updateBannerStatus(banner);
    }

    @Test
    public void test3() {
        Album album = new Album();
        album.setId(7);
        Album album1 = albumMapper.selectOne(album);

    }

    @Test
    public void testExport() {
        List<Album> albums = albumMapper.queryAllAlbumByPage(1, 7);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑详情", "音频"),
                Album.class, albums);
        try {
            workbook.write(new FileOutputStream(new File("F:/test.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}