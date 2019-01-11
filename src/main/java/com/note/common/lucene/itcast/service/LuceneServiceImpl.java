package com.note.common.lucene.itcast.service;


import com.note.common.lucene.itcast.bean.Article;
import com.note.common.lucene.itcast.bean.ResultBean;
import com.note.common.lucene.itcast.dao.LuceneDao;
import org.apache.lucene.document.Document;

import java.io.IOException;

public class LuceneServiceImpl implements LuceneService {
    private LuceneDao dao = new LuceneDao();

    public ResultBean<Article> getResultBean(String keywords, int firstResult, int maxResult) {
        try {
            return dao.getdocumentList(keywords, firstResult, maxResult);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteIndexById(String Id) {
        dao.deleteIndexById(Id);
    }

    public void updateIndexById(Document document) {
        try {
            dao.updateIndexById(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
