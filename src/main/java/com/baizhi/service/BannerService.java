package com.baizhi.service;

import com.baizhi.dto.BannerDto;
import com.baizhi.entity.Banner;

public interface BannerService {
    BannerDto queryBannerByPage(int curPage, int pageSize);
    void updateBannerStatus(Banner banner);
    void insertOneBanner(Banner banner);
    void deleteOneBannerById(String bannerId);
}
