package com.baizhi.mapper;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {
    //两个最新的文章方法
    List<Article> queryArticleRandom2();

    //查询不属于登陆用户的上师文章
    List<Article> queryOtherGuruArticle(@Param("guruId") Integer guruId);
}
