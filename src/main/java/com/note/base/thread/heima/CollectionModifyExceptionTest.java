package com.note.base.thread.heima;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {
    public static void main(String[] args) {
        // 先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器
        Collection users = new CopyOnWriteArrayList();
        users.add(new User("张三", 28));
        users.add(new User("李四", 25));
        users.add(new User("王五", 31));
        Iterator itrUsers = users.iterator();

        while (itrUsers.hasNext()) {
            System.out.println("aaaa");
            User user = (User) itrUsers.next();
            if ("李四".equals(user.getName())) {
                users.remove(user);
            } else {
                System.out.println(user);
            }
        }
    }
}
