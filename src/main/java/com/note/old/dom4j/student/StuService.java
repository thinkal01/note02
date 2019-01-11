package com.note.old.dom4j.student;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.List;

public class StuService {

    //查询 根据id查询学生信息
    public static Student03 getStu(String id) throws Exception {
        /*
         * 1、创建解析器
         * 2、得到document
         *
         * 3、获取到所有的id
         * 4、返回的是list集合，遍历list集合
         * 5、得到每一个id的节点
         * 6、id节点的值
         * 7、判断id的值和传递的id值是否相同
         * 8、如果相同，先获取到id的父节点stu
         * 9、通过stu获取到name age值
         *
         * */
        //创建解析器
        SAXReader saxReader = new SAXReader();
        //得到document
        Document document = saxReader.read("src/student.xml");
        //获取所有的id
        List<Node> list = document.selectNodes("//id");
        //创建student对象
        Student03 student = new Student03();
        //遍历list
        for (Node node : list) { //node是每一个id节点
            //得到id节点的值
            String idv = node.getText();
            //判断id是否相同
            if (idv.equals(id)) {
                //得到id的父节点 stu
                Element stu = node.getParent();
                //通过stu获取name和age
                String namev = stu.element("name").getText();
                String agev = stu.element("age").getText();
                student.setId(idv);
                student.setName(namev);
                student.setAge(agev);
            }
        }
        return student;
    }

    //增加
    public static void addStu(Student03 student) throws Exception {
        /*
         * 1、创建解析器
         * 2、得到document
         * 3、获取到根节点
         * 4、在根节点上面创建stu标签
         * 5、在stu标签上面依次添加id name age
         * 6、在id name age上面依次添加值
         *
         * 7、回写xml
         * */
        //创建解析器
        SAXReader saxReader = new SAXReader();
        //得到document
        Document document = saxReader.read("src/student.xml");
        //得到根节点
        Element root = document.getRootElement();
        //在根节点上面添加stu
        Element stu = root.addElement("stu");
        //在stu标签上面依次添加id name age标签
        Element id1 = stu.addElement("id");
        Element name1 = stu.addElement("name");
        Element age1 = stu.addElement("age");
        //在id name age上面依次添加值
        id1.setText(student.getId());
        name1.setText(student.getName());
        age1.setText(student.getAge());
        //回写xml
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/student.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    //删除 根据学生的id删除
    public static void delStu(String id) throws Exception {
        /*
         * 1、创建解析器
         * 2、得到document
         *
         * 3、获取到所有的id
         * 	使用xpath //id 返回 list集合
         * 4、遍历list集合
         * 5、判断集合里面的id和传递的id是否相同
         * 6、如果相同，把id所在的stu删除
         *
         * */
        //创建解析器
        SAXReader saxReader = new SAXReader();
        //得到document
        Document document = saxReader.read("src/student.xml");
        //获取所有的id  xpath: //id
        List<Node> list = document.selectNodes("//id");
        //遍历list集合
        for (Node node : list) { //node是每一个id的元素
            //得到id的值
            String idv = node.getText();
            //判断idv和传递的id是否相同
            if (idv.equals(id)) { //id相同
                //得到stu节点
                Element stu = node.getParent();
                //获取stu的父节点
                Element student = stu.getParent();
                //删除stu
                student.remove(stu);
            }
        }
        //回写xml
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/student.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

}