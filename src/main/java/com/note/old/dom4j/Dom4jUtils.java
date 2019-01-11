package com.note.old.dom4j;

import com.note.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileOutputStream;

public class Dom4jUtils {

    // public static final String PATH = "src/p1.xml";
    public static final String PATH = FileUtil.getCurrentPath("p1.xml", Dom4jUtils.class);

    //返回document
    public static Document getDocument(String path) {
        try {
            //创建解析器
            SAXReader reader = new SAXReader();
            //得到document
            Document document = reader.read(path);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //回写xml的方法
    public static void xmlWriters(String path, Document document) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPath() {
        Dom4jUtils.getDocument("");
    }
}