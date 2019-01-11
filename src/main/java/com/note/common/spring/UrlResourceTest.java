package com.note.common.spring;

import com.note.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import java.util.Iterator;
import java.util.List;

public class UrlResourceTest {
    @Test
    public void urlResource() throws Exception {
        // 创建一个Resource对象，指定从文件系统里读取资源
        UrlResource ur = new UrlResource("file:" + FileUtil.getResourcePath("book.xml"));
        // 获取该资源的简单信息
        System.out.println(ur.getFilename());
        System.out.println(ur.getDescription());
        // 创建基于SAX的dom4j解析器
        SAXReader reader = new SAXReader();
        Document doc = reader.read(ur.getFile());
        // 获取根元素
        Element el = doc.getRootElement();
        List l = el.elements();
        // 遍历根元素的全部子元素
        for (Iterator it = l.iterator(); it.hasNext(); ) {
            // 每个节点都是<书>节点
            Element book = (Element) it.next();
            List ll = book.elements();
            // 遍历<书>节点的全部子节点
            for (Iterator it2 = ll.iterator(); it2.hasNext(); ) {
                Element eee = (Element) it2.next();
                System.out.println(eee.getText());
            }
        }
    }

    @Test
    public void classPathResource() throws Exception {
        // 创建一个Resource对象，从类加载路径里读取资源
        ClassPathResource cr = new ClassPathResource("book.xml");
        // 获取该资源的简单信息
        System.out.println(cr.getFilename());
        System.out.println(cr.getDescription());
        // 创建基于SAX的dom4j解析器
        SAXReader reader = new SAXReader();
        Document doc = reader.read(cr.getFile());
        // 获取根元素
        Element el = doc.getRootElement();
        List l = el.elements();
        // 遍历根元素的全部子元素
        for (Iterator it = l.iterator(); it.hasNext(); ) {
            // 每个节点都是<书>节点
            Element book = (Element) it.next();
            List ll = book.elements();
            // 遍历<书>节点的全部子节点
            for (Iterator it2 = ll.iterator(); it2.hasNext(); ) {
                Element eee = (Element) it2.next();
                System.out.println(eee.getText());
            }
        }

    }
}
