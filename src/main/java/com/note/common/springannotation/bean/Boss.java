package com.note.common.springannotation.bean;

import org.springframework.stereotype.Component;

//默认加在ioc容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作
@Component
public class Boss {

    private Car car;

    //构造器要用的组件，也是从容器中获取
    //@Autowired,只有一个有参构造可省略注解
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss...有参构造器");
    }

    public Car getCar() {
        return car;
    }

    //标注在方法，Spring容器创建当前对象，就会调用方法，完成赋值；
    //@Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss [car=" + car + "]";
    }

}
