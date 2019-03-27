package com.note.common.springannotation.config;

import com.note.common.springannotation.bean.Car;
import com.note.common.springannotation.bean.Color;
import com.note.common.springannotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配;
 * Spring利用依赖注入（DI），完成对IOC容器中中各个组件的依赖关系赋值；
 * 由后置处理器AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能；
 * BookService{
 *  @Autowired : Spring定义的
 *  BookDao  bookDao;
 * }
 * 1）、@Autowired：自动注入：
 *   1）、默认优先按照类型去容器中找对应的组件:applicationContext.getBean(BookDao.class)
 *   2）、如果找到多个相同类型的组件，再将属性名作为组件id去容器中查找:applicationContext.getBean("bookDao")
 * 3）、@Qualifier("bookDao")：使用@Qualifier指定需要装配的组件id
 * 4）、自动装配默认属性必须存在；可以使用@Autowired(required=false);
 * 5）、@Primary：让Spring进行自动装配的时候，默认使用首选的bean；
 *     也可以继续使用@Qualifier指定需要装配的bean的名字
 * <p>
 * 2）、Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
 * @Resource: 可以和@Autowired一样实现自动装配功能；默认是按照组件名称进行装配的；
 * 不支持@Primary和required=false;
 * @Inject: 需要导入javax.inject的包，和Autowired功能一样。没有required=false功能；
 * 3）、@Autowired:构造器，参数，方法，属性；都是从容器中获取参数组件的值
 * 1）、[标注在方法位置]：@Bean+方法参数；参数可以自动从容器中获取;
 * 2）、[标在构造器上]：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略
 * 3）、放在参数位置：
 * <p>
 * 4）、自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
 * 自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware；
 * xxxAware：功能使用xxxProcessor 后置处理器处理
 * ApplicationContextAware==》ApplicationContextAwareProcessor
 */
@Configuration
@ComponentScan({"com.note.common.springannotation.service", "com.note.common.springannotation.dao",
        "com.note.common.springannotation.controller", "com.note.common.springannotation.bean"})
public class MainConfigOfAutowired {

    // 优先注入
    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    /**
     * @Bean 标注的方法创建对象时，方法参数值自动从容器中获取
     */
    @Bean
    public Color color(Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }

}
