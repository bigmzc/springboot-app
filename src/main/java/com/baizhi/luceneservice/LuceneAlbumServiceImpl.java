package com.baizhi.luceneservice;

import com.baizhi.entity.Album;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LuceneAlbumServiceImpl implements LuceneAlbumService {
    @Override
    public void createIndex(Album album) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromAlbum = getDocFromAlbum(album);
        try {
            indexWriter.addDocument(docFromAlbum);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    @Override
    public void deleteIndex(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    @Override
    public void updateIndex(Album album) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromAlbum = getDocFromAlbum(album);
        try {
            indexWriter.updateDocument(new Term("id", album.getId().toString()), docFromAlbum);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    @Override
    public List<Album> searchIndex(String keyWords) {
        //使用IkAnalyzer分词器
        String[] field = {"title", "author", "brief"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, field, new IKAnalyzer());
        MatchAllDocsQuery matchAllDocsQuery = new MatchAllDocsQuery();
        Query parse = null;
        try {
            parse = multiFieldQueryParser.parse(keyWords);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Album> list = new ArrayList<>();
        try {
            TopDocs topDocs = indexSearcher.search(matchAllDocsQuery, 100);
            System.out.println("=============");
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Album album = getAlbumFromDoc(document);
                System.out.println(album);
                System.out.println("=============");
                list.add(album);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Document getDocFromAlbum(Album album) {
        Document document = new Document();
        document.add(new IntField("id", album.getId(), Field.Store.YES));
        document.add(new StringField("title", album.getTitle(), Field.Store.YES));
        document.add(new StringField("author", album.getAuthor(), Field.Store.YES));
        document.add(new StringField("brief", album.getBrief(), Field.Store.YES));
        return document;
    }

    @Override
    public Album getAlbumFromDoc(Document document) {
        Album album = new Album();
        album.setId(Integer.valueOf(document.get("id")));
        album.setTitle(document.get("title"));
        album.setAuthor(document.get("author"));
        album.setBrief(document.get("brief"));
        return album;
    }
}
