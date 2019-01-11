package com.note.old.web.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * SimpleTagSupport它实现了SimpleTag接口。
 * 它已经把所有的tomcat传递的数据都保存起来了！而且还提供了get方法供子类调用！
 */
public class MyTag2 extends SimpleTagSupport {
    @Override
    public void doTag() throws IOException {
        this.getJspContext().getOut().print("再 Hello 一次 Tag!");
    }
}