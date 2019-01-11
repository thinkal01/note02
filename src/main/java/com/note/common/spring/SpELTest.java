package com.note.common.spring;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpELTest {
    public static void main(String[] args) {
        // 创建一个ExpressionParser对象，用于解析表达式
        ExpressionParser parser = new SpelExpressionParser();
        // 最简单的字符串表达式
        Expression exp = parser.parseExpression("'HelloWorld'");
        // HelloWorld
        System.out.println("'HelloWorld'的结果： " + exp.getValue());

        // 调用方法的表达式
        exp = parser.parseExpression("'HelloWorld'.concat('!')");
        // HelloWorld!
        System.out.println("'HelloWorld'.concat('!')的结果： " + exp.getValue());

        // 调用对象的getter方法
        exp = parser.parseExpression("'HelloWorld'.bytes");
        // [B@4b9e255
        System.out.println("'HelloWorld'.bytes的结果： " + exp.getValue());

        // 访问对象的属性(d相当于HelloWorld.getBytes().length)
        exp = parser.parseExpression("'HelloWorld'.bytes.length");
        // 10
        System.out.println("'HelloWorld'.bytes.length的结果：" + exp.getValue());

        // 使用构造器来创建对象
        exp = parser.parseExpression("new String('helloworld')" + ".toUpperCase()");
        // HELLOWORLD
        System.out.println("new String('helloworld')" + ".toUpperCase()的结果是： " + exp.getValue(String.class));

        Person person = new Person(1, "孙悟空", new Date());
        exp = parser.parseExpression("name");
        // 以指定对象作为root来计算表达式的值
        // 相当于调用person.name表达式的值
        // 孙悟空
        System.out.println("以person为root，name表达式的值是： " + exp.getValue(person, String.class));

        exp = parser.parseExpression("name=='孙悟空'");
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        // 将person设为Context的root对象
        ctx.setRootObject(person);
        // 以指定Context来计算表达式的值
        // true
        System.out.println(exp.getValue(ctx, Boolean.class));

        List<Boolean> list = new ArrayList<>();
        list.add(true);
        EvaluationContext ctx2 = new StandardEvaluationContext();
        // 将list设置成EvaluationContext的一个变量
        ctx2.setVariable("list", list);
        // 修改list变量的第一个元素的值
        parser.parseExpression("#list[0]").setValue(ctx2, "false");
        // list集合的第一个元素被改变
        // false
        System.out.println("list集合的第一个元素为：" + parser.parseExpression("#list[0]").getValue(ctx2));
    }
}

class Person {
    private Integer id;
    private String name;
    private Date birth;

    // 无参数的构造器
    public Person() {
    }

    // 初始化全部成员变量的构造器
    public Person(Integer id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    // id的setter和getter方法
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    // name的setter和getter方法
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // birth的setter和getter方法
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getBirth() {
        return this.birth;
    }
}