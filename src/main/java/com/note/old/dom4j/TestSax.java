package com.note.old.dom4j;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class TestSax {

    public static void main(String[] args) throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、创建解析器
         * 3、执行parse方法
         *
         * 4、自己创建一个类，继承DefaultHandler
         * 5、重写类里面的三个方法
         * */
        //创建解析器工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //创建解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();
        //执行parse方法
        saxParser.parse("src/p1.xml", new MyDefault2());
    }
}

//实现获取所有的name元素的值
class MyDefault2 extends DefaultHandler {

    boolean flag = false;
    int idx = 1;

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        //判断qName是否是name元素
        if ("name".equals(qName)) {
            flag = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        //当flag值是true时候，表示解析到name元素
        //索引是1
        if (flag == true && idx == 2) {
            System.out.println(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        //把flag设置成false，表示name元素结束
        if ("name".equals(qName)) {
            flag = false;
            idx++;
        }
    }

}

class MyDefault1 extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.print("<" + qName + ">");
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.print("</" + qName + ">");
    }

}











