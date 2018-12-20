package com.baizhi.service;

import com.baizhi.dto.BannerDto;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public BannerDto queryBannerByPage(int curPage, int pageSize) {
        BannerDto bannerDto = new BannerDto();
        int count = bannerMapper.selectCount(new Banner());
        bannerDto.setTotal(count);
        bannerDto.setRows(bannerMapper.queryBannerByPage(curPage, pageSize));
        return bannerDto;
    }

    @Override
    public void updateBannerStatus(Banner banner) {
        bannerMapper.updateByPrimaryKeySelective(banner);
        //int i = bannerMapper.updateByPrimaryKey(banner);
    }
}
