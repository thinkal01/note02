package com.note.base.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) {
        // 以当前路径来创建Path对象
        Path path = Paths.get(".");
        System.out.println("path里包含的路径数量：" + path.getNameCount()); // 1
        System.out.println("path的根路径：" + path.getRoot()); // null

        // 获取path对应的绝对路径。
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println("absolutePath的根路径：" + absolutePath.getRoot());
        System.out.println("absolutePath里包含的路径数量：" + absolutePath.getNameCount());
        System.out.println(absolutePath.getName(3));

        // 以多个String来构建Path对象
        Path path2 = Paths.get("g:", "publish", "codes");
        System.out.println(path2);
    }
}
