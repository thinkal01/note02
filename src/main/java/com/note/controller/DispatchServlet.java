package com.note.controller;

import java.util.ArrayList;
import java.util.List;

// 模拟处理器适配器处理过程
public class DispatchServlet {
    public static List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    public DispatchServlet() {
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
    }

    public void doDispatch() {
        //此处模拟SpringMVC从request取handler对象
        //不论实现何种Controller，总能经过适配器得到想要的结果
        // HttpController controller = new HttpController();
        // AnnotationController controller = new AnnotationController();
        SimpleController controller = new SimpleController();
        //得到对应适配器
        HandlerAdapter adapter = getHandler(controller);
        //通过适配器执行对应的controller对应方法
        adapter.handle(controller);
    }

    public HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter adapter : this.handlerAdapters) {
            if (adapter.supports(controller)) {
                return adapter;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new DispatchServlet().doDispatch();
    }
}

// 模拟一个Controller
interface Controller {
}

//以下是三种Controller实现
class HttpController2 implements Controller {
    public void doHttpHandler() {
        System.out.println("http...");
    }
}

class SimpleController implements Controller {
    public void doSimplerHandler() {
        System.out.println("simple...");
    }
}

class AnnotationController implements Controller {
    public void doAnnotationHandler() {
        System.out.println("annotation...");
    }
}

// 模拟一个handlerAdapter
interface HandlerAdapter {
    boolean supports(Object handler);

    void handle(Object handler);
}

class SimpleHandlerAdapter implements HandlerAdapter {
    public void handle(Object handler) {
        ((SimpleController) handler).doSimplerHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }
}

class HttpHandlerAdapter implements HandlerAdapter {
    public void handle(Object handler) {
        ((HttpController2) handler).doHttpHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }
}

class AnnotationHandlerAdapter implements HandlerAdapter {
    public void handle(Object handler) {
        ((AnnotationController) handler).doAnnotationHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }
}