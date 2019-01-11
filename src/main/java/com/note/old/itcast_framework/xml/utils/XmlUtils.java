package com.note.old.itcast_framework.xml.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {
    public static <T> T toBean(Element e, Class<T> clazz) {
        try {
            Map map = toMap(e);
            T bean = clazz.newInstance();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Element toElement(Object bean, String eleName, String... childs) {
        List<String> list = Arrays.asList(childs);
        return toElement(bean, eleName, list);
    }

    public static Element toElement(Object bean, String eleName, List<String> childs) {
        /*
         * 1创建元素对象
         * 2把bean的所有属性获取过来 Field[] fs = c.getDeclaredFields();
         * 循环遍历fs，获取其中每个的名字 获取属性名，很容易：fs[i].getName();
         * 获取属性值，不能去使用fs[i].get()方法，因为属性是私有的，而应该使用getXXX()方法获取。
         * 但这很不方便，好在我们有BeanUtils
         */
        try {
            Element e = DocumentHelper.createElement(eleName);//创建元素对象
            Class c = bean.getClass();//获取bean类型
            Field[] fs = c.getDeclaredFields();//获取bean的所有属性反射对象
            for (Field f : fs) {
                String name = f.getName();//获取属性名称
                Object value = BeanUtils.getProperty(bean, name);//获取该属性的值
                if (value == null) {
                    continue;
                }
                if (childs.contains(name)) {//子元素
                    e.addElement(name).setText(String.valueOf(value));
                } else {//属性
                    e.addAttribute(name, String.valueOf(value));
                }
            }
            return e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * <student number="N_1001"> <name>zs</name> <age>23</age> <sex>male</sex>
     * </student> {number=N_1001, name=zs, age=23, sex=male}
     *
     * 可以是纯属性元素 也可以包含只有文本的子元素 <a><b><c>xxx</c></b></a>，这样的东西不转了
     */
    public static Map<String, String> toMap(Element e) {
        Map<String, String> map = new LinkedHashMap<>();
        /*
         * 循环遍历e的所有属性 循环遍历e的所有子元素（条件：只是纯文本内容的子元素）
         * 把e的所有属性添加到map中
         */
        List<Attribute> attrs = e.attributes();
        for (Attribute a : attrs) {
            map.put(a.getName(), a.getValue());
        }
        /*
         * 把e的所有子元素（纯属文本内容的子元素）添加到map中
         */
        List<Element> eles = e.elements();
        for (Element ele : eles) {
            if (ele.isTextOnly()) {
                map.put(ele.getName(), ele.getText());
            }
        }
        return map;
    }

    /*
     * {number=N_1001, name=zs, age=23, sex=male} 1，元素名称我不知道！ <student
     * number="N_1001" name="zs" age="23" sex="male"/> <student>
     * <number>N_1001</number> <name>zs</name> <age>23</age> <sex>male</sex>
     * </student>
     *
     * {number=N_1001, name=zs, age=23, sex=male} toElement(map, "student",
     * [name, age, sex]);
     */
    public static Element toElement(Map<String, String> map, String eleName,
                                    List<String> childs) {
        Element e = DocumentHelper.createElement(eleName);
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (childs.contains(key)) {
                e.addElement(key).setText(value);
            } else {
                e.addAttribute(key, value);
            }
        }

        return e;
    }

    public static Element toElement(Map<String, String> map, String eleName,
                                    String... childs) {
        List<String> list = Arrays.asList(childs);
        return toElement(map, eleName, list);
    }

    public static Document getDocument(String xmlName) {
        return getDocument(xmlName, true);
    }

    public static Document getDocument(String xmlName, boolean relative) {
        try {
            if (relative) {
                xmlName = Thread.currentThread().getContextClassLoader().getResource(xmlName).getPath();
            }
            return new SAXReader().read(xmlName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDocument(Document doc, String xmlName) {
        saveDocument(doc, xmlName, true);
    }

    public static void saveDocument(Document doc, String xmlName,
                                    boolean relative) {
        try {
            // 创建格式器，\t表示缩进使用的字符，true表示为换行
            OutputFormat format = new OutputFormat("\t", true);
            format.setTrimText(true);
            if (relative) {
                xmlName = Thread.currentThread().getContextClassLoader()
                        .getResource(xmlName).getPath();
            }
            // 创建输出流
            OutputStream out = new FileOutputStream(xmlName);
            // 把输出流转换成字符流，其中使用了utf-8编码
            Writer wout = new OutputStreamWriter(out, "utf-8");
            // 使用输出流和格式化器创建XML输出流
            XMLWriter writer = new XMLWriter(wout, format);
            // 输出Document对象
            writer.write(doc);
            // 关闭流！
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
