package com.baizhi.service;

import com.baizhi.dto.AlbumDto;
import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public AlbumDto queryAllAlbum(int curPage, int pageSize) {
        //总记录数
        AlbumDto albumDto = new AlbumDto();
        int count = albumMapper.selectCount(new Album());
        //PageHelper.startPage(curPage, pageSize);
        List<Album> albums = albumMapper.queryAllAlbumByPage(curPage, pageSize);
        albumDto.setTotal(count);
        albumDto.setRows(albums);
        return albumDto;
    }

    @Override
    public void insertOneAlbum(Album album) {
        albumMapper.insert(album);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Album queryOneAlbumById(Integer id) {
        Album album = new Album();
        album.setId(id);
        Album album1 = albumMapper.selectOne(album);
        return album1;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> getAllAlbumAndAudio() {
        int curPage = 1;
        AlbumDto albumDto = new AlbumDto();
        int count = albumMapper.selectCount(new Album());
        List<Album> albums = albumMapper.queryAllAlbumByPage(curPage, count);
        return albums;
    }
}
