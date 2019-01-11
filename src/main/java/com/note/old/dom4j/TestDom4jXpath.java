package com.note.old.dom4j;

import org.dom4j.Document;
import org.dom4j.Node;

import java.util.List;

public class TestDom4jXpath {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//		test1();
        test2();
    }

    //使用xpath实现：获取第一个p1下面的name的值
    public static void test2() throws Exception {
        /*
         * 1、得到document
         * 2、直接使用selectSingleNode方法实现
         * 	- xpath ： //p1[@id1='aaaa']/name
         */
        //得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        //直接使用selectSingleNode方法实现
        Node name1 = document.selectSingleNode("//p1[@id1='aaaa']/name"); //name的元素
        //得到name里面的值
        String s1 = name1.getText();
        System.out.println(s1);
    }

    //查询xml中所有name元素的值
    public static void test1() throws Exception {
        /*
         * 1、得到document
         * 2、直接使用selectNodes("//name")方法得到所有的name元素
         *
         * */
        //得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        //使用selectNodes("//name")方法得到所有的name元素
        List<Node> list = document.selectNodes("//name");
        //遍历list集合
        for (Node node : list) {
            //node是每一个name元素
            //得到name元素里面的值
            String s = node.getText();
            System.out.println(s);
        }
    }

}
