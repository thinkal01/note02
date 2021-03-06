package com.note.common.lucene;

import com.note.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LuceneManagerTest {
    @Test
    public void testIndexCreate() throws Exception {
        //创建文档列表,保存多个Docuemnt
        List<Document> docList = new ArrayList<>();

        //指定文件所在目录
        File dir = new File(FileUtil.getResourcePath("searchsource"));
        //循环文件夹取出文件
        for (File file : dir.listFiles()) {
            //文件名称
            String fileName = file.getName();
            //文件内容
            String fileContext = FileUtils.readFileToString(file, Charset.defaultCharset());
            //文件大小
            Long fileSize = FileUtils.sizeOf(file);
            //文档对象,文件系统中的一个文件就是一个Docuemnt对象
            Document doc = new Document();

            //第一个参数:域名
            //第二个参数:域值
            //第三个参数:是否存储,是为yes,不存储为no
            //是否分词:要,因为它要索引,并且它不是一个整体,分词有意义
            //是否索引:要,因为要通过它来进行搜索
            //是否存储:要,因为要直接在页面上显示
            TextField nameField = new TextField("fileName", fileName, Store.YES);

            //是否分词: 要,因为要根据内容进行搜索,并且它分词有意义
            //是否索引: 要,因为要根据它进行搜索
            //是否存储: 可以要也可以不要,不存储搜索完内容就提取不出来
            TextField contextFiled = new TextField("fileContext", fileContext, Store.NO);

            //是否分词: 要, 因为数字要对比,搜索文档的时候可以搜大小, lucene内部对数字进行了分词算法
            //是否索引: 要, 因为要根据大小进行搜索
            //是否存储: 要, 因为要显示文档大小
            LongField sizeFiled = new LongField("fileSize", fileSize, Store.YES);

            //将所有的域都存入文档中
            doc.add(nameField);
            doc.add(contextFiled);
            doc.add(sizeFiled);

            //将文档存入文档集合中
            docList.add(doc);
        }

        //创建分词器,StandardAnalyzer标准分词器,标准分词器对英文分词效果很好,对中文是单字分词
        Analyzer analyzer = new IKAnalyzer();
        //指定索引和文档存储的目录
        Directory directory = FSDirectory.open(new File("E:\\dic"));
        //创建写对象的初始化对象
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        //创建索引和文档写对象
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //将文档加入到索引和文档的写对象中
        for (Document doc : docList) {
            indexWriter.addDocument(doc);
        }
        //提交
        indexWriter.commit();
        //关闭流
        indexWriter.close();
    }

    @Test
    public void testIndexDel() throws Exception {
        //创建分词器,StandardAnalyzer标准分词器,标准分词器对英文分词效果很好,对中文是单字分词
        Analyzer analyzer = new IKAnalyzer();
        //指定索引和文档存储的目录
        Directory directory = FSDirectory.open(new File("E:\\dic"));
        //创建写对象的初始化对象
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        //创建索引和文档写对象
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //删除所有
        //indexWriter.deleteAll();

        //根据名称进行删除
        //Term词元,就是一个词, 第一个参数:域名, 第二个参数:要删除含有此关键词的document数据
        indexWriter.deleteDocuments(new Term("fileName", "apache"));

        //提交
        indexWriter.commit();
        //关闭
        indexWriter.close();
    }

    /**
     * 更新就是按照传入的Term进行搜索,如果找到结果那么删除,将更新的内容重新生成一个Document对象
     * 如果没有搜索到结果,那么将更新的内容直接添加一个新的Document对象
     *
     * @throws Exception
     */
    @Test
    public void testIndexUpdate() throws Exception {
        //创建分词器,StandardAnalyzer标准分词器,标准分词器对英文分词效果很好,对中文是单字分词
        Analyzer analyzer = new IKAnalyzer();
        //指定索引和文档存储的目录
        Directory directory = FSDirectory.open(new File("E:\\dic"));
        //创建写对象的初始化对象
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
        //创建索引和文档写对象
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //根据文件名称进行更新
        Term term = new Term("fileName", "web");
        //更新的对象
        Document doc = new Document();
        doc.add(new TextField("fileName", "xxxxxx", Store.YES));
        doc.add(new TextField("fileContext", "think in java xxxxxxx", Store.NO));
        doc.add(new LongField("fileSize", 100L, Store.YES));

        //更新
        indexWriter.updateDocument(term, doc);
        //提交
        indexWriter.commit();
        //关闭
        indexWriter.close();
    }

    @Test
    //查看标准分析器的分词效果
    public void testTokenStream() throws Exception {
        //创建一个标准分析器对象
        Analyzer analyzer = new StandardAnalyzer();
        //获得tokenStream对象
        //第一个参数：域名，可以随便给一个
        //第二个参数：要分析的文本内容
        TokenStream tokenStream = analyzer.tokenStream("test", "The Spring Framework provides a comprehensive programming and configuration model.");
        //添加一个引用，可以获得每个关键词
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        //将指针调整到列表的头部
        tokenStream.reset();
        //遍历关键词列表，通过incrementToken方法判断列表是否结束
        while (tokenStream.incrementToken()) {
            //关键词的起始位置
            System.out.println("start->" + offsetAttribute.startOffset());
            //取关键词
            System.out.println(charTermAttribute);
            //结束位置
            System.out.println("end->" + offsetAttribute.endOffset());
        }
        tokenStream.close();
    }
}