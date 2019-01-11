package com.note.common.lucene.itcast.listener;

import com.note.common.lucene.itcast.dao.LuceneDao;
import com.note.common.lucene.itcast.utils.Constants;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class StartReptileListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String urlName = Constants.URL;//链接的地址...
            URL U = new URL(urlName);
            /**
             * <html></html>
             * <xml></xml>
             * Soap=http +xml
             * httpClient
             */
            //java.net 用来发送http请求...是用java代码来模拟http请求...
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
                    LuceneDao.addIndex(document);
                }
            }
            LuceneDao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
