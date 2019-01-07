package com.baizhi.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dto.UserDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Province;
import com.baizhi.luceneservice.LuceneAlbumService;
import com.baizhi.mapper.*;
import com.baizhi.service.BannerService;
import com.baizhi.service.RoleService;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
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
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseEnvTest {


    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    UserService userService;

    @Autowired
    LuceneAlbumService luceneAlbumService;

    @Autowired
    private RoleService roleService;

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
    public void test4() {
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建表 参数为表名
        Sheet sheet = workbook.createSheet("a1");

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

    @Test
    public void test5() {
        /*Integer integer = userMapper.queryActiversUser(7);
        System.out.println(integer);*/
        UserDto userDto = userService.queryActiveUsers();
        System.out.println(userDto);
    }

    @Test
    public void test6() {
        Map<String, List<Province>> listMap = userService.queryUsersDistrubution();
        System.out.println(listMap);
    }

    @Test
    public void test7() {
        Article article = new Article();
        article.setGuruId(1);
        List<Article> articles = articleMapper.select(article);
        System.out.println(articles);
    }

    @Test
    public void test8() {
        Album album = new Album();
        album.setId(Integer.parseInt("1"));
        Album album1 = albumMapper.selectOne(album);
        System.out.println(album1);
    }

    @Test
    public void testQuerySearch() {
        List<Album> albums = luceneAlbumService.searchIndex("带");
        /*System.out.println(albums);
        LuceneAlbumService luceneAlbumService1 = new LuceneAlbumServiceImpl();
        List<Album> albums = luceneAlbumService1.searchIndex("今晚");
        System.out.println(albums);*/
    }

}