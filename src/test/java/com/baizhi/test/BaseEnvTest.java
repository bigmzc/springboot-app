package com.baizhi.test;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseEnvTest {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private BannerService bannerService;

    @Test
    public void test1() {

        int count = bannerMapper.selectCount(new Banner());
        System.out.println(count);
    }

    @Test
    public void test2(HttpSession session) {
        Banner banner = new Banner();
        banner.setId(9);
        banner.setStatus("Y");
        bannerService.updateBannerStatus(banner);
    }
}