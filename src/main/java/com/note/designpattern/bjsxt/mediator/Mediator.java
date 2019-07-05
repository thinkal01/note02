package com.note.designpattern.bjsxt.mediator;

public interface Mediator {

    void register(String dname, Department d);

    void command(String dname);
}
