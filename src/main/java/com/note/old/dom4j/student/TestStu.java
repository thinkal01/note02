package com.note.old.dom4j.student;

public class TestStu {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//		testAdd();
//		testDel();
        testSelect();
    }

    //测试查询方法
    public static void testSelect() throws Exception {
        Student03 stu = StuService.getStu("100");
        System.out.println(stu.toString());
    }

    //测试删除方法
    public static void testDel() throws Exception {
        StuService.delStu("103");
    }

    //测试添加方法
    public static void testAdd() throws Exception {
        //设置值
        Student03 stu = new Student03();
        stu.setId("103");
        stu.setName("wangwu");
        stu.setAge("40");
        StuService.addStu(stu);
    }

}
