package com.baizhi.service;

import com.baizhi.dto.AlbumDto;

public interface AlbumService {
    AlbumDto queryAllAlbum(int curPage, int pageSize);
}
