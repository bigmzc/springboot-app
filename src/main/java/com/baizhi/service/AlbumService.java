package com.baizhi.service;

import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;

public interface AlbumService {
    AlbumDto queryAllAlbum(int curPage, int pageSize);

    void insertOneAlbum(Album album);

    Album queryOneAlbumById(Integer id);
}
