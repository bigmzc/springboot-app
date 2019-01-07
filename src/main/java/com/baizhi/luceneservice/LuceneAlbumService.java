package com.baizhi.luceneservice;

import com.baizhi.entity.Album;
import org.apache.lucene.document.Document;

import java.util.List;

public interface LuceneAlbumService {
    void createIndex(Album album);

    void deleteIndex(String id);

    void updateIndex(Album album);

    List<Album> searchIndex(String keyWords);

    Document getDocFromAlbum(Album album);

    Album getAlbumFromDoc(Document document);
}
