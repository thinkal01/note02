package com.note.base.io;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Date;
import java.util.List;

public class AttributeViewTest {
    public static void main(String[] args) throws Exception {
        // 获取将要操作的文件
        Path testPath = Paths.get("AttributeViewTest.java");
        // 获取访问基本属性的BasicFileAttributeView
        BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        // 获取访问基本属性的BasicFileAttributes
        BasicFileAttributes basicAttribs = basicView.readAttributes();
        // 访问文件的基本属性
        System.out.println("创建时间：" + new Date(basicAttribs.creationTime().toMillis()));
        System.out.println("最后访问时间：" + new Date(basicAttribs.lastAccessTime().toMillis()));
        System.out.println("最后修改时间：" + new Date(basicAttribs.lastModifiedTime().toMillis()));
        System.out.println("文件大小：" + basicAttribs.size());

        // 获取访问文件属主信息的FileOwnerAttributeView
        FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
        // 获取该文件所属的用户
        System.out.println(ownerView.getOwner());
        // 获取系统中guest对应的用户
        UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
        // 修改用户
        ownerView.setOwner(user);

        // 获取访问自定义属性的FileOwnerAttributeView
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath, UserDefinedFileAttributeView.class);
        List<String> attrNames = userView.list();
        // 遍历所有的自定义属性
        for (String name : attrNames) {
            ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            System.out.println(name + "--->" + value);
        }
        // 添加一个自定义属性
        userView.write("发行者", Charset.defaultCharset().encode("疯狂Java联盟"));
        // 获取访问DOS属性的DosFileAttributeView
        DosFileAttributeView dosView = Files.getFileAttributeView(testPath, DosFileAttributeView.class);
        // 将文件设置隐藏、只读
        dosView.setHidden(true);
        dosView.setReadOnly(true);
    }
}