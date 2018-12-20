package com.baizhi.service;

import com.baizhi.dto.BannerDto;

public interface BannerService {
    BannerDto queryBannerByPage(int curPage, int pageSize);
}
