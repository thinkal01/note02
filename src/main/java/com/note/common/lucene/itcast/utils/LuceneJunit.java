package com.note.common.lucene.itcast.utils;

import com.note.common.lucene.itcast.bean.Article;
import com.note.common.lucene.itcast.bean.ResultBean;
import com.note.common.lucene.itcast.service.LuceneService;
import com.note.common.lucene.itcast.service.LuceneServiceImpl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKTokenizer;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


/**
 * luncene 测试
 */
public class LuceneJunit {

    LuceneService luceneService = new LuceneServiceImpl();

    @Test
    public void testAddIndex() {
        rss("http://news.qq.com/newsgn/rss_newsgn.xml");
    }

    public String rss(String rssXML) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String urlName = rssXML;
            URL U = new URL(urlName);
            URLConnection connection = U.openConnection();
            connection.connect();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(connection.getInputStream());
            Element root = doc.getRootElement();
            Element foo;
            Element subfoo;
            int ios = 1;
            for (Iterator<Element> i = root.elementIterator("channel"); i.hasNext(); ) {
                foo = i.next();
                for (Iterator<Element> j = foo.elementIterator("item"); j.hasNext(); ) {
                    System.out.println("--------------------------------------------------------------");
                    subfoo = j.next();
                    String link = subfoo.elementTextTrim("link");
                    String title = subfoo.elementTextTrim("title").trim();
                    String content = subfoo.elementTextTrim("description");
                    String author = subfoo.elementTextTrim("author");
                    System.out.println("author===" + author);
                    System.out.println("link===" + link);
                    System.out.println("description===" + content);
                    org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();
                    document.add(new StringField("Id", String.valueOf(ios++), Store.YES));
                    document.add(new TextField("title", title, Store.YES));
                    document.add(new TextField("content", content, Store.YES));
                    document.add(new StringField("author", author, Store.YES));
                    document.add(new StringField("link", link, Store.YES));
                    document.add(new StringField("date", sdf.format(new Date()), Store.YES));
                    // LuceneDao.addIndex(document);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testQuery() {
        ResultBean<Article> rs = luceneService.getResultBean("中国", 0, 10);
        System.out.println(rs.getResult().size());
        for (Article article : rs.getResult()) {
            System.out.println("Id===" + article.getId());
            System.out.println(article.getConent());
        }
    }

    @Test
    public void testDelete() {
        luceneService.deleteIndexById("7");
    }

    @Test
    public void updateIndex() {
        org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();
        document.add(new StringField("Id", "8", Store.YES));
        document.add(new TextField("content", "中国类风湿关节炎的患病率已经达到", Store.YES));
        luceneService.updateIndexById(document);
    }

    @Test
    public void optimise() {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, LuceneUtil.getAnalyzer());
        //创建索引库优化对象...
        LogMergePolicy logMergePolicy = new LogByteSizeMergePolicy();
        //值越小，搜索的时候越快,创建索引的时候越慢
        //值越大，搜索的时候越慢，创建索引的时候越快。
        logMergePolicy.setMergeFactor(3);
        //设置segment最大合并文档(Document)数
        //值较小有利于追加索引的速度
        //值较大,适合批量建立索引和更快的搜索
        logMergePolicy.setMaxMergeDocs(1000);
        indexWriterConfig.setMergePolicy(logMergePolicy);
    }

    @Test
    public void testRMDirectory() throws IOException {
        Directory directory = new RAMDirectory();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, LuceneUtil.getAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();
        document.add(new StringField("Id", "8", Store.YES));
        document.add(new TextField("content", "中国类风湿关节炎的患病率已经达到", Store.YES));
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Test
    public void testRedirectory() throws IOException {
//		//创建索引目录
//		Directory directory=FSDirectory.open(new File("/indexDir/"));
//		//通过此对象可将硬盘上的索引读到内存中,涉及io操作...
//		IOContext context=new IOContext();
//		//将磁盘中的索引加载到内存当中，以后每次操作索引的时候，直接操作内存中的索引即可，不用操作硬盘
//		Directory ramDir = new RAMDirectory(directory,context);
        //构造索引读取器
//		IndexReader indexReader=DirectoryReader.open(ramDir);
        //构造索引搜索对象。。。
//		IndexSearcher indexSearcher=new IndexSearcher(indexReader);
    }

    @Test
    public void test() throws IOException {
        //使用lucene 自带的标准分词器...
//		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_44);
//		Analyzer analyzer=new ChineseAnalyzer();中文分词器...
//		Analyzer analyzer=new CJKAnalyzer(Version.LUCENE_44);//new CJKAnalyzer(Version.LUCENE_44);//二分法分词...
        IKAnalyzer analyzer = new IKAnalyzer();
        String keywords = "传智播客lucene 是全文检索的框架";
        testAnalyer(analyzer, keywords);
    }

    public void testAnalyer(Analyzer analyzer, String keyWord) throws IOException {
        System.out.println("当前使用的分词器：" + analyzer.getClass().getSimpleName());
        IKTokenizer tokenStream = (IKTokenizer) analyzer.tokenStream("content", new StringReader(keyWord));
        tokenStream.addAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println(new String(charTermAttribute.toString()));
        }
    }

}
