package com.baizhi.test;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        System.out.println(album1);
    }
}