package com.note.common.lucene.itcast.utils;

import com.note.common.lucene.itcast.bean.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * lucene 工具类，用于获取获取操作索引的对象。
 */
public class LuceneUtil {
    //索引库的配置信息,当前版本，使用的分词器
    private static IndexWriterConfig indexWriterConfig;
    //指定分词...
    private static Analyzer analyzer = new IKAnalyzer();//使用词库分词
    //索引存放的目录
    private static Directory directory;
    private static File file = new File(Constants.file);
    private static IndexWriter indexWriter = null;

    static {
        try {
            directory = FSDirectory.open(file);
            indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
            indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
            indexWriter = new IndexWriter(directory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分词器....
     */
    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    /**
     * 获取索引操作对象。。。
     */
    public static IndexWriter getIndexWriter() {
        /*IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return indexWriter;
    }

    /**
     * 获取索引读取对象。。。
     */
    public static IndexSearcher getIndexSearcher() {
        IndexSearcher indexSearcher = null;
        try {
            IndexReader indexReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexSearcher;
    }

    /**
     * 关闭操作索引的对象
     */
    public static void closeIndexWriter(IndexWriter indexWriter) {
        if (indexWriter != null) {
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Article documentToArticle(Document document) {
        Article article = new Article();
        article.setId(document.get("Id"));
        article.setAuthor(document.get("author"));
        article.setUrl(document.get("link"));
        article.setConent(document.get("content"));
        article.setTitle(document.get("title"));
        return article;
    }

}
