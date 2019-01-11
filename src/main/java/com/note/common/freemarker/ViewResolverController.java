package com.note.common.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewResolverController {

    /**
     * 视图解析器工作流程：
     * 目标方法的返回值不管是 String, Map, ModelMap, Model, 还是 ModelAndView。 SpringMVC 都会在内部将他们装配成ModelAndView对象，
     * 然后借助视图解析器 ViewResolver，得到最终的逻辑视图对象View。最终的物理视图可以是jsp，excel，表单 等各种表现形式的视图。
     * <p>
     * 视图：视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户。视图对象由视图解析器负责实例化。由于视图是无状态的，所以他们
     * 不会有线程安全的问题。
     * <p>
     * 视图解析器： SpringMVC 为逻辑视图的解析提供了不同的策略，可以在Spring WEB 上下文中配置一种或者多种解析策略，
     * 并指定他们的先后顺序。每一种映射策略对应一个具体的视图解析器的实现类。
     * 视图解析器的作用比较单一：将逻辑视图解析为一个具体的视图对象。
     * <p>
     * org.springframework.web.servlet.view.InternalResourceViewResolver 是jsp最为参见的视图解析器。
     * <p>
     * 若项目中使用了jstl，则SpringMVC 会自动把视图由 InternalResourceViewResolver 转为 JstlView
     * 若使用jstl的fmt标签，则需要在SpringMVC的配置文件中配置国际资源化文件。
     * 若希望直接响应通过SpringMVC渲染页面，可以使用 mvc:view-controller
     */

    @RequestMapping("/testRedirect")
    public String testRedirect() {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^重定向到apistudy.jsp页面，地址栏URL改变");
        return "redirect:apiStudy/testModelAndView";
    }

    @RequestMapping("/testForward")
    public String testForward() {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^转发到apistudy.jsp页面，地址栏URL不变");
        return "forward:apiStudy/testModelAndView";
    }

    @RequestMapping("/testCustomViewResolver")
    public String testCustomViewResolver() {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^进入到自定义的视图解析器中，返回值必须是类名首字母小写");
        return "customViewResolver";
    }

}
