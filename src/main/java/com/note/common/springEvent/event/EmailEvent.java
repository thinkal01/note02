package com.note.common.springEvent.event;

import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent {
    private String address;
    private String text;

    public EmailEvent(Object source) {
        super(source);
    }

    // 初始化全部成员变量的构造器
    public EmailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    // address的setter和getter方法
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    // text的setter和getter方法
    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}