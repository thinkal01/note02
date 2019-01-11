package com.note.old;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

public class Xml01 {

    public static void main(String[] args) {
        // 获取 DocumentBuilderFactory 的新实例。
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 指定由此代码生成的解析器将忽略注释
        dbf.setIgnoringComments(true);
        // 设置忽略空格
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db = null;
        Document document = null;
        try {
            // 获取DocumentBuilder的实例
            db = dbf.newDocumentBuilder();
            File file = new File("D:\\itcastworkspace\\xmldom\\src\\cn\\itcast\\dom\\students.xml");
            document = db.parse(file);

            // 按文档顺序返回包含在文档中且具有给定标记名称的所有 Element 的 NodeList
            NodeList nodeList = document.getElementsByTagName("name");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // 输出标记的值
                System.out.println(node.getFirstChild().getNodeValue());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class SAXReadXML {
        public static void main(String[] args) {
            // 创建SAXParserFactory对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            try { // 创建SAXParser对象
                SAXParser saxParser = spf.newSAXParser();
                File file = new File("D:\\itcastworkspace\\xmlSax\\src\\cn\\itcast\\sax\\students.xml");
                /*解析文件
                 *new XMLContentHandler() 事件处理程序*/
                saxParser.parse(file, new XMLContentHandler());
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class XMLContentHandler extends DefaultHandler {
        // 当前元素中的数据
        private String currentData;

        // 取得元素数据
        public void characters(char[] ch, int start, int length) {
            currentData = new String(ch, start, length);
        }

        // 在解析整个文档结束时调用
        public void endDocument() {
            System.out.println("结束文档");
        }

        // 在解析元素结束时调用
        public void endElement(String uri, String localName, String name) {
            System.out.println("节点数据  *************************"
                    + this.currentData);
            System.out.println("结束元素 ************" + name);
        }

        // 在解析整个文档开始时调用
        public void startDocument() {
            System.out.println("开始文档");
        }

        // 在解析元素开始时调用
        public void startElement(String uri, String localName, String name, Attributes attributes) {
            System.out.println("开始元素 ************" + name);
        }
    }

}
