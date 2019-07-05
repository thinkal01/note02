package com.note.designpattern.bjsxt.command;

public class Client {
    public static void main(String[] args) {
        // 命令类
        Command c = new ConcreteCommand(new Receiver());
        // 调用者
        Invoke i = new Invoke(c);
        i.call();

        // new Receiver().action();
    }
}
