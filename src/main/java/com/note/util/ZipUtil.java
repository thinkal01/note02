package com.note.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;

public class ZipUtil {
    private static Log log = LogFactory.getLog(ZipUtil.class);

    /**
     * 压缩文件列表
     */
    public static boolean zip(File[] files, String zipName, String encoding) {
        ZipOutputStream out = null;
        ZipEntry entry = null;
        BufferedInputStream bis = null;
        byte[] buffer = new byte[4096]; // 创建缓冲区
        int size = 0;
        File zipFile = new File(zipName);
        if (zipFile.exists()) {
            zipFile.delete();
        }
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            out.setEncoding(encoding);//防止中文乱码
            for (File file : files) {
                entry = new ZipEntry(file.getName());
                //entry.setUnixMode(644);//解决linux下乱码
                out.putNextEntry(entry);
                bis = new BufferedInputStream(new FileInputStream(file));
                while ((size = bis.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }
                out.flush();
                out.closeEntry();//结束当前entry,即压缩文件里的一个压缩文件结束
                bis.close();
            }
            return true;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            return false;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Throwable e) {
                log.error("压缩文件后关闭流异常:" + e.getMessage(), e);
            }
        }
    }


    /**
     * 压缩文件列表
     */
    public static boolean zipfolderFile(File folderFile, File zipCardFile, String zipName, String encoding) {
        ZipOutputStream out = null;
        ZipEntry entry = null;
        BufferedInputStream bis = null;
        byte[] buffer = new byte[4096]; // 创建缓冲区
        int size = 0;
        File zipFile = new File(zipName);
        if (zipFile.exists()) {
            zipFile.delete();
        }
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            out.setEncoding(encoding);//防止中文乱码
            File[] files = folderFile.listFiles();
            for (File file : files) {
                out.putNextEntry(new ZipEntry(folderFile.getName() + File.separator + file.getName()));
                bis = new BufferedInputStream(new FileInputStream(file));
                while ((size = bis.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }
                out.flush();
                out.closeEntry();//结束当前entry,即压缩文件里的一个压缩文件结束
                bis.close();
            }

            entry = new ZipEntry(zipCardFile.getName());
            //entry.setUnixMode(644);//解决linux下乱码
            out.putNextEntry(entry);
            bis = new BufferedInputStream(new FileInputStream(zipCardFile));
            while ((size = bis.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
            out.flush();
            out.closeEntry();//结束当前entry,即压缩文件里的一个压缩文件结束
            bis.close();
            entry.clone();

            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Throwable e) {
            }
        }
    }


    /**
     * 解压文件到path目录
     *
     * @param file     类型File 待解压的文件
     * @param path     类型String 解压后的目录
     * @param encoding 类型String 压缩文件编码类型
     */
    public static boolean unzip(File file, String path, String encoding) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        int size = 0;
        byte[] buffer = new byte[4096];
        try {
            ZipFile zipFile = new ZipFile(file, encoding);//encoding对应压缩文件编码,解决中文乱码
            for (Enumeration<?> enu = zipFile.getEntries(); enu.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) enu.nextElement();
                if (entry.isDirectory()) {
                    File dir = new File(path, entry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                }
                inputStream = zipFile.getInputStream(entry);
                File f = new File(path, entry.getName());
                File parentFile = f.getParentFile();
                if (!parentFile.exists()) {//判断父目录是否已创建
                    parentFile.mkdirs();
                }
                if (f.exists()) {
                    f.delete();
                }
                fileOutputStream = new FileOutputStream(f);
                while ((size = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, size);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            zipFile.close();//
            inputStream.close();
            return true;
        } catch (IOException e) {
            log.error("解压文件异常: " + e.getMessage(), e);
            return false;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("关闭流异常: " + e.getMessage(), e);
            }
        }
    }

    /**
     * 解压大文件到fileOutputDir目录
     *
     * @param fileFullPath  原文件路径
     * @param fileOutputDir 文件输出路径
     * @param encoding      编码格式
     * @return
     */
    public static boolean ExtractBigFile(String fileFullPath, String fileOutputDir, String encoding) {
        long startTime = System.currentTimeMillis();
        System.out.println("startTime：" + startTime / 1000);
        InputStream inputStream = null;
        OutputStream os = null;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(new File(fileFullPath), encoding);//encoding对应压缩文件编码,解决中文乱码
            for (Enumeration<?> enu = zipFile.getEntries(); enu.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) enu.nextElement();
                if (entry.isDirectory()) {
                    File dir = new File(fileOutputDir, entry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                }
                inputStream = zipFile.getInputStream(entry);
                File f = new File(fileOutputDir, entry.getName());
                File parentFile = f.getParentFile();
                if (!parentFile.exists()) {//判断父目录是否已创建
                    parentFile.mkdirs();
                }
                if (f.exists()) {
                    f.delete();
                }
                os = new BufferedOutputStream(new FileOutputStream(f));
                int size = 0;
                byte[] buffer = new byte[2048];
                while ((size = inputStream.read(buffer)) > 0) {
                    os.write(buffer, 0, size);
                }
                os.flush();
                os.close();
            }
            zipFile.close();//
            inputStream.close();
            long endTime = System.currentTimeMillis();
            System.out.println("总耗时：" + ((endTime - startTime) / 1000));
            return true;
        } catch (IOException e) {
            log.error("解压文件异常: " + e.getMessage(), e);
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException e) {
                log.error("关闭流异常: " + e.getMessage(), e);
            }
        }
    }
}
