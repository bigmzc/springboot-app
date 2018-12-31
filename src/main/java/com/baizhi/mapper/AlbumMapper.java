package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface AlbumMapper extends Mapper<Album> {
    List<Album> queryAllAlbumByPage(@Param("curPage") int curPage, @Param("pageSize") int pageSize);

    //6个最新专辑-sql只封装需要的字段
    List<Album> queryAlbumLasted6();
}
