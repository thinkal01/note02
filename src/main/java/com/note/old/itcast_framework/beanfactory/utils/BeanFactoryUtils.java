package com.note.old.itcast_framework.beanfactory.utils;

import com.note.old.itcast_framework.beanfactory.BeanFactory;
import com.note.old.itcast_framework.beanfactory.cfg.BeanConfig;
import com.note.old.itcast_framework.beanfactory.cfg.PropertyConfig;
import com.note.old.itcast_framework.xml.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;

public class BeanFactoryUtils {
    public static void load(BeanFactory factory, String xmlName) {
        Document doc = XmlUtils.getDocument(xmlName);
        List<Node> beanEleList = doc.selectNodes("//bean");
        for (Node node : beanEleList) {
            Element beanEle = (Element) node;
            BeanConfig bc = XmlUtils.toBean(beanEle, BeanConfig.class);
            List<Element> propEleList = beanEle.elements();
            // 把所有的PropertyConfig添加到BeanConfig中
            for (Element propEle : propEleList) {// 遍历<bean>中每个<property>元素
                PropertyConfig pc = XmlUtils.toBean(propEle, PropertyConfig.class);
                bc.addPropertyConfig(pc);
            }
            factory.addBeanConfig(bc);
        }
    }
}
