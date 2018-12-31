package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Error;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AppServiceImpl implements AppService {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object queryFirstPage(String uid, String type, String sub_type) {
        if (uid == null || type == null) {
            return new Error("args error");
        } else {
            if (type.equals("all")) {
                Map<String, Object> map = new HashMap<>();
                //5张轮播图--imgPath使用前端拼接
                List<Banner> banners = bannerMapper.queryBanner5();
                map.put("header", banners);

                List<Album> albums = albumMapper.selectAll();
                map.put("album", albums);

                List<Article> articles = articleMapper.selectAll();
                map.put("artical", articles);
                return map;
            } else if (type.equals("wen")) {
                Map<String, Object> map = new HashMap<>();
                //放入所有专辑
                List<Album> albums = albumMapper.selectAll();
                map.put("album", albums);
                return map;
            } else if (type.equals("si")) {
                if (sub_type.equals("ssyj")) {
                    //该用户对应的上师文章 select from article where guru_id = (select guru.id from user where id = uid)
                    //此处使用假数据guruId=1
                    Article article = new Article();
                    article.setGuruId(1);
                    List<Article> articles = articleMapper.select(article);
                    Map<String, Object> map = new HashMap<>();
                    map.put("artical", articles);
                    return map;
                } else if (sub_type.equals("xmfy")) {
                    //其他上师文章 guruID≠1,假数据
                    List<Article> articles = articleMapper.queryOtherGuruArticle(1);
                    Map<String, Object> map = new HashMap<>();
                    map.put("artical", articles);
                    return map;
                } else {
                    return new Error("sub_type need data");
                }
            } else {
                return new Error("无效的type类型");
            }
        }
    }
}
