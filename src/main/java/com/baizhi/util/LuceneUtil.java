package com.baizhi.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneUtil {
    private static Directory directory;
    private static final Version version = Version.LUCENE_44;
    private static Analyzer analyzer;
    private static IndexWriterConfig indexWriterConfig;

    //索引存放位置-F:index
    static {
        try {
            //version = Version.LUCENE_44;
            analyzer = new IKAnalyzer();
            directory = FSDirectory.open(new File("F:/index"));
            indexWriterConfig = new IndexWriterConfig(version, analyzer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IndexWriter getIndexWriter() {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public static IndexSearcher getIndexSearcher() {
        IndexSearcher indexSearcher = null;
        try {
            IndexReader reader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexSearcher;
    }

    public static void commit(IndexWriter indexWriter) {
        try {
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(IndexWriter indexWriter) {
        try {
            indexWriter.rollback();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
