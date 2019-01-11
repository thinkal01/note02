package com.note.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 实现文件[夹]解压
 */
public class UnZipFile {

    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     *
     * @param zipFile 待解压的zip文件
     * @param destDir 指定目录
     */
    public static void unZipFiles(File zipFile, String destDir) {
        ZipFile zip = null;//解决中文文件夹乱码
        try {
            zip = new ZipFile(zipFile, Charset.forName("GBK"));
            String name = zip.getName().substring(zip.getName().lastIndexOf('\\') + 1, zip.getName().lastIndexOf('.'));

            File pathFile = new File(destDir + name);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }

            for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (destDir + name + "/" + zipEntryName).replaceAll("\\*", "/");

                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }

                // 判断文件全路径是否为文件夹,如果是上面已经创建,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                // System.out.println(outPath);

                FileOutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                zip.close();
            } catch (IOException e) {
            }
        }

        System.out.println("******************解压完毕********************");
        return;
    }

    public static void main(String[] args) {
        unZipFiles(new File("G:\\1.zip"), "G:/unzip/");
    }

}