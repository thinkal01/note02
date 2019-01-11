package com.note.algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class StudentDemo {
    public static void main(String[] args) throws IOException {
        // 创建集合对象
        TreeSet<Student02> ts = new TreeSet<>((s1, s2) -> {
            int num = s2.getSum() - s1.getSum();
            int num2 = num == 0 ? s1.getChinese() - s2.getChinese() : num;
            int num3 = num2 == 0 ? s1.getMath() - s2.getMath() : num2;
            int num4 = num3 == 0 ? s1.getEnglish() - s2.getEnglish() : num3;
            int num5 = num4 == 0 ? s1.getName().compareTo(s2.getName()) : num4;
            return num5;
        });

        // 键盘录入学生信息存储到集合
        for (int x = 1; x <= 5; x++) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请录入第" + x + "个的学习信息");
            System.out.println("姓名：");
            String name = sc.nextLine();
            System.out.println("语文成绩：");
            int chinese = sc.nextInt();
            System.out.println("数学成绩：");
            int math = sc.nextInt();
            System.out.println("英语成绩：");
            int english = sc.nextInt();

            // 创建学生对象
            Student02 s = new Student02();
            s.setName(name);
            s.setChinese(chinese);
            s.setMath(math);
            s.setEnglish(english);

            // 把学生信息添加到集合
            ts.add(s);
        }

        // 遍历集合，把数据写到文本文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));
        bw.write("学生信息如下：");
        bw.newLine();
        bw.flush();
        bw.write("姓名,语文成绩,数学成绩,英语成绩");
        bw.newLine();
        bw.flush();
        for (Student02 s : ts) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.getName()).append(",").append(s.getChinese()).append(",").append(s.getMath()).append(",").append(s.getEnglish());
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }
        // 释放资源
        bw.close();
        System.out.println("学习信息存储完毕");
    }
}

class Student02 {
    // 姓名
    private String name;
    // 语文成绩
    private int chinese;
    // 数学成绩
    private int math;
    // 英语成绩
    private int english;

    public Student02() {
        super();
    }

    public Student02(String name, int chinese, int math, int english) {
        super();
        this.name = name;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getSum() {
        return this.chinese + this.math + this.english;
    }
}
