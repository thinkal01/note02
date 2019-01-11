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
 * ���ڲ�������
 */
public class LuceneDao {
    private static IndexWriter indexWriter;
    private static IndexSearcher indexSearcher;

    /**
     * �������
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
     * ɾ������
     */
    public void deleteIndexById(String Id) {
        //term����������С��λ������ʾ�ĵ���һ�����term����������ɣ�����ʾ�Ĵ����������������ֵ�field��
        Term term = new Term("Id", Id);
        try {
            indexWriter = LuceneUtil.getIndexWriter();//��ȡ���������Ķ���
            indexWriter.deleteDocuments(term);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LuceneUtil.closeIndexWriter(indexWriter);//�رղ������󡣡���

    }

    /**
     * �޸�����
     */
    public void updateIndexById(Document document) throws IOException {
        Term term = new Term("Id", document.get("Id"));
        indexWriter = LuceneUtil.getIndexWriter();
        indexWriter.updateDocument(term, document);
        LuceneUtil.closeIndexWriter(indexWriter);
    }

    /**
     * ��������
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
     * ���ݹؼ���������ҳ
     */
    public ResultBean<Article> getdocumentList(String keywords, int firstResult, int maxResult) throws Exception {
        indexSearcher = LuceneUtil.getIndexSearcher();//��ȡ��������
        String[] fields = new String[]{"title", "content", "link", "author"};//���ݶ���ֶν�������
        //������������ѯ��,��ƴ��ѯ����
        QueryParser parser = new MultiFieldQueryParser(fields, LuceneUtil.getAnalyzer());
        //�����ַ����õ�query����
        Query query = parser.parse(keywords);
        //��ȡ���������
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
         *   * Query:ָ����ѯ����
         *   * String field:ָ�����ĸ��ֶ��Ͻ��и�����content��ʾ�����ݵ��ֶ��Ͻ��и���
         */
        QueryScorer scorer = new QueryScorer(query, "title");
        Highlighter highlighter = new Highlighter(formatter, scorer);
        int endResult = Math.min(scoreDocs.length, firstResult + maxResult);
        /**
         * ���ø���Ч��
         * new SimpleFragmenter():ָ�����ɵ��ı��󷵻�ժҪ�Ĵ�С��Ĭ��ǰ100������
         * ���Դ���һ��int���͵Ĳ���������ָ��ժҪ�Ĵ�С
         */
        int fragmenter = 200;
        highlighter.setTextFragmenter(new SimpleFragmenter(fragmenter));
        for (int i = firstResult; i < endResult; i++) {
            //��ȡ���ݵĵ÷�
            System.out.println("�÷֣�" + scoreDocs[i].score);
            //��ȡ�������ж�Ӧ�����ݱ�ţ�Ψһ�ı�ţ�
            int doc = scoreDocs[i].doc;
            System.out.println("docid=" + doc);
            //ʹ���������Ψһ��Ż�ȡ��Ӧ������Document
            Document document = indexSearcher.doc(doc);

            /****************************************************/
            /**
             * ��ȡ�������ֵ
             *   * ����1���ִ���
             *   * ����2��String����ָ�����ĸ��ֶ������ɸ���Ч��
             *   * ����3��String�������ĸ��ֶε�ֵ���ؽ��и���
             *
             * * ����ֵ��
             *    text�����ظ�������ı���һ��ֻ����һ���ı�
             *       * ����и�����ֵ���ڣ�text��ֵ�ͷ��ظ������ֵ
             *       * ���û�и�����ֵ���ڣ�text��ֵ�ͷ���null
             */
            String content = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "content", document.get("content"));//ֻ�ܸ���ĳһ���ֶ�
            String title = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "title", document.get("title"));//ֻ�ܸ���ĳһ���ֶ�
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
            //���������ֵ��ֵ��content�ֶ�
            // document.get("content").setValue(text);
            /****************************************************/
            //��Documentת����Article����Article����ֵ
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
        ResultBean<Article> rs = new LuceneDao().getdocumentList("�й�", 10, 21);
        for (Article a : rs.getResult()) {
            System.out.println(a.getConent());
            System.out.println(a.getTitle());
        }
    }
}
