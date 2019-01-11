package com.note.base.thread;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class jvm {
    public static void main(String[] args) {
        // 打印当前使用的年轻代和老年代垃圾收集器
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }
    }
}
