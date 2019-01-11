package com.note.common.lucene.itcast.dao;

import com.note.common.lucene.itcast.bean.Article;
import com.note.common.lucene.itcast.bean.ResultBean;
import com.note.common.lucene.itcast.utils.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于操作索引
 */
public class LuceneDao {
    private static IndexWriter indexWriter;
    private static IndexSearcher indexSearcher;

    /**
     * 添加索引
     */
    public static void addIndex(Document document) {
        try {
            indexWriter = LuceneUtil.getIndexWriter();
            indexWriter.addDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() throws IOException {
        indexWriter = LuceneUtil.getIndexWriter();
        indexWriter.commit();
        indexWriter.close();
    }

    /**
     * 删除索引
     */
    public void deleteIndexById(String Id) {
        //term是搜索的最小单位，它表示文档的一个词语，term由两部分组成：它表示的词语和这个词语所出现的field。
        Term term = new Term("Id", Id);
        try {
            indexWriter = LuceneUtil.getIndexWriter();//获取操作索引的对象
            indexWriter.deleteDocuments(term);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LuceneUtil.closeIndexWriter(indexWriter);//关闭操作对象。。。

    }

    /**
     * 修改索引
     */
    public void updateIndexById(Document document) throws IOException {
        Term term = new Term("Id", document.get("Id"));
        indexWriter = LuceneUtil.getIndexWriter();
        indexWriter.updateDocument(term, document);
        LuceneUtil.closeIndexWriter(indexWriter);
    }

    /**
     * 查找索引
     */
    public Document findDocumentById(String Id) throws IOException {
        indexSearcher = LuceneUtil.getIndexSearcher();
        Query query = new TermQuery(new Term("Id", Id));
        TopDocs docs = indexSearcher.search(query, 1);
        ScoreDoc[] scoreDocs = docs.scoreDocs;
        Document document = null;
        for (int i = 0; i < scoreDocs.length; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];
            int id = scoreDoc.doc;
            document = indexSearcher.doc(id);
        }
        return document;
    }

    /**
     * 根据关键字搜索分页
     */
    public ResultBean<Article> getdocumentList(String keywords, int firstResult, int maxResult) throws Exception {
        indexSearcher = LuceneUtil.getIndexSearcher();//获取索引对象
        String[] fields = new String[]{"title", "content", "link", "author"};//根据多个字段进行搜索
        //创建多条件查询器,组拼查询条件
        QueryParser parser = new MultiFieldQueryParser(fields, LuceneUtil.getAnalyzer());
        //根据字符串得到query对象
        Query query = parser.parse(keywords);
        //获取搜索结果集
        ResultBean<Article> resultBean = new ResultBean<>();
        TopDocs hits = indexSearcher.search(query, firstResult + maxResult);
        resultBean.setTotal(hits.totalHits);
        ScoreDoc[] scoreDocs = hits.scoreDocs;
        Article article;
        List<Article> list = new ArrayList<>();
        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        /**
         * int firstReslut
         * int maxResult
         *
         * 0,10
         * 10,10
         * 20,10
         */
        /**
         * Scorer
         *   * Query:指定查询条件
         *   * String field:指定在哪个字段上进行高亮，content表示在内容的字段上进行高亮
         */
        QueryScorer scorer = new QueryScorer(query, "title");
        Highlighter highlighter = new Highlighter(formatter, scorer);
        int endResult = Math.min(scoreDocs.length, firstResult + maxResult);
        /**
         * 设置高亮效果
         * new SimpleFragmenter():指定生成的文本后返回摘要的大小，默认前100个文字
         * 可以传递一个int类型的参数，从新指定摘要的大小
         */
        int fragmenter = 200;
        highlighter.setTextFragmenter(new SimpleFragmenter(fragmenter));
        for (int i = firstResult; i < endResult; i++) {
            //获取数据的得分
            System.out.println("得分：" + scoreDocs[i].score);
            //获取索引库中对应的数据编号（唯一的编号）
            int doc = scoreDocs[i].doc;
            System.out.println("docid=" + doc);
            //使用索引库的唯一编号获取对应的数据Document
            Document document = indexSearcher.doc(doc);

            /****************************************************/
            /**
             * 获取高亮后的值
             *   * 参数1：分词器
             *   * 参数2（String）：指定在哪个字段中生成高亮效果
             *   * 参数3（String）：将哪个字段的值返回进行高亮
             *
             * * 返回值：
             *    text：返回高亮后的文本，一次只高亮一次文本
             *       * 如果有高亮的值存在，text的值就返回高亮后的值
             *       * 如果没有高亮的值存在，text的值就返回null
             */
            String content = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "content", document.get("content"));//只能高亮某一个字段
            String title = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "title", document.get("title"));//只能高亮某一个字段
            if (title == null) {
                title = document.get("title");
                if (title != null && title.length() > fragmenter) {
                    title = title.substring(0, fragmenter);
                }
            }
            if (content == null) {
                content = document.get("content");
                if (content != null && content.length() > fragmenter) {
                    content = content.substring(0, fragmenter);
                }
            }
            //将高亮后的值赋值给content字段
            // document.get("content").setValue(text);
            /****************************************************/
            //将Document转换成Article，向Article对象赋值
            article = new Article();
            article.setConent(content);
            article.setTitle(title);
            String link = document.get("link");
            article.setUrl(link);
            list.add(article);
        }
        resultBean.setResult(list);
        return resultBean;
    }


    public static void main(String[] args) throws Exception {
        ResultBean<Article> rs = new LuceneDao().getdocumentList("中国", 10, 21);
        for (Article a : rs.getResult()) {
            System.out.println(a.getConent());
            System.out.println(a.getTitle());
        }
    }
}
