package com.note.common.lucene.itcast.service;


import com.note.common.lucene.itcast.bean.Article;
import com.note.common.lucene.itcast.bean.ResultBean;
import org.apache.lucene.document.Document;

/**
 * 搜索
 *
 * @ 作者 zhuwu@itcast.cn
 */
public interface LuceneService {
    /**
     * 根据关键字搜索....
     *
     * @param keywords
     * @return
     */
    ResultBean<Article> getResultBean(String keywords, int firstReslut, int maxResult);

    /**
     * 删除索引根据Id
     *
     * @param Id
     */
    void deleteIndexById(String Id);

    /**
     * 更新索引，
     *
     * @param document
     */
    void updateIndexById(Document document);

}
