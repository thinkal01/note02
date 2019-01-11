package com.note.base.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO01 {
    public void fileList() {
        /* 获取当前目录下的文件以及文件夹的名称，包含隐藏文件。
         * 调用list方法的 File对象中封装的必须是目录。
         * 否则会发生NullPointerException
         * 如果访问的系统级目录也会发生空指针异常。
         * 如果目录存在但是没有内容，会返回一个数组，但是长度为0.
         */
        File file = new File("f:\\");
        String[] names = file.list();
        for (String name : names) {
            System.out.println(name);
        }
    }

    public void testFilter() {
        File file = new File("e:\\");
        String[] strArray = file.list((dir, name) -> new File(dir, name).isFile() && name.endsWith(".jpg"));
    }

    // 删除文件夹
    public static void deleteFolder(File srcFolder) {
        File[] fileArray = srcFolder.listFiles();

        if (fileArray != null) {
            for (File file : fileArray) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    System.out.println(file.getName() + "---" + file.delete());
                }
            }

            System.out.println(srcFolder.getName() + "---" + srcFolder.delete());
        }
    }

    // 通过后缀查找文件
    public static void searchFiles(File srcFolder, String suffix) {
        File[] fileArray = srcFolder.listFiles();

        for (File file : fileArray) {
            if (file.isDirectory()) {
                searchFiles(file, suffix);
            } else {
                if (file.getName().endsWith(suffix)) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }

    // 复制文件
    public void copyFile() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("c:\\a.txt");
            fos = new FileOutputStream("d:\\b.txt");
            byte[] bys = new byte[1024];
            int len;
            while ((len = fis.read(bys)) != -1) {
                fos.write(bys, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 高效字节流一次读写一个字节数组
    public static void bufferStream(String srcString, String destString) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        bis.close();
    }

    public void inputStreamReader() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"), "UTF-8");

        // 一次读取一个字符
        int ch = 0;
        while ((ch = isr.read()) != -1) {
            System.out.print((char) ch);
        }

        isr.close();
    }

    public void InputStreamReaderCopy() throws IOException {
        // 封装数据源
        InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"));
        // 封装目的地
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("b.txt"));

        char[] chs = new char[1024];
        int len = 0;
        while ((len = isr.read(chs)) != -1) {
            osw.write(chs, 0, len);
            // osw.flush();
        }

        // 释放资源
        osw.close();
        isr.close();
    }

    /*
     * 由于我们常见的操作都是使用本地默认编码，所以，不用指定编码。
     * 而转换流的名称有点长，所以，Java就提供了其子类供我们使用。
     * OutputStreamWriter = FileOutputStream + 编码表(GBK)
     * FileWriter = FileOutputStream + 编码表(GBK)
     *
     * InputStreamReader = FileInputStream + 编码表(GBK)
     * FileReader = FileInputStream + 编码表(GBK)
     */
    public void fileReaderCopy() throws IOException {
        // 封装数据源
        FileReader fr = new FileReader("a.txt");
        // 封装目的地
        FileWriter fw = new FileWriter("b.txt");

        char[] chs = new char[1024];
        int len = 0;
        while ((len = fr.read(chs)) != -1) {
            fw.write(chs, 0, len);
            // fw.flush();
        }

        // 释放资源
        fw.close();
        fr.close();
    }

    public void bufferedReaderLine() throws IOException {
        // 封装数据源
        BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        // 封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        // 释放资源
        bw.close();
        br.close();
    }

    public void bufferedReaderChar() throws IOException {
        // 封装数据源
        BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        // 封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));

        char[] chs = new char[1024];
        int len;
        while ((len = br.read(chs)) != -1) {
            bw.write(chs, 0, len);
            bw.flush();
        }

        // 释放资源
        bw.close();
        br.close();
    }

    public static void copyFolder() throws IOException {
        // 封装目录
        File srcFolder = new File("e:\\java");
        // 封装目的地
        File destFolder = new File("e:\\jad");
        // 如果目的地目录不存在，就创建
        if (!destFolder.exists()) {
            destFolder.mkdir();
        }

        // 获取该目录下的java文件的File数组
        File[] fileArray = srcFolder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isFile() && name.endsWith(".java");
            }
        });

        // 遍历该File数组，得到每一个File对象
        for (File file : fileArray) {
            // 数据源：e:\java\DataTypeDemo.java
            // 目的地：e:\\jad\DataTypeDemo.java
            String name = file.getName();
            File newFile = new File(destFolder, name);
            copyFile(file, newFile);
        }

        // 在目的地目录下改名
        File[] destFileArray = destFolder.listFiles();
        for (File destFile : destFileArray) {
            // e:\jad\DataTypeDemo.java
            // e:\\jad\\DataTypeDemo.jad
            String name = destFile.getName(); //DataTypeDemo.java
            String newName = name.replace(".java", ".jad");//DataTypeDemo.jad

            File newFile = new File(destFolder, newName);
            destFile.renameTo(newFile);
        }
    }

    private static void copyFile(File file, File newFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));

        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        bis.close();
    }

    public static void copyFolders() throws IOException {
        // 封装数据源File
        File srcFile = new File("E:\\JavaSE\\day21\\code\\demos");
        // 封装目的地File
        File destFile = new File("E:\\");

        // 复制文件夹的功能
        copyFolder(srcFile, destFile);
    }

    private static void copyFolder(File srcFile, File destFile) throws IOException {
        // 判断该File是文件夹还是文件
        if (srcFile.isDirectory()) {
            // 文件夹
            File newFolder = new File(destFile, srcFile.getName());
            newFolder.mkdir();

            // 获取该File对象下的所有文件或者文件夹File对象
            File[] fileArray = srcFile.listFiles();
            for (File file : fileArray) {
                copyFolder(file, newFolder);
            }
        } else {
            // 文件
            File newFile = new File(destFile, srcFile.getName());
            copyFile(srcFile, newFile);
        }
    }

    public void listAll() {
        File dir = new File("G:\\Files\\0\\notes");
        listAll(dir, 0);
    }

    // 列出目录
    public static void listAll(File dir, int level) {
        System.out.println(getSpace(level) + dir.getName());
        //获取指定目录下当前的所有文件夹或者文件对象
        level++;
        File[] files = dir.listFiles();
        for (int x = 0; x < files.length; x++) {
            if (files[x].isDirectory()) {
                listAll(files[x], level);
            } else {
                System.out.println(getSpace(level) + files[x].getName());
            }
        }
    }

    private static String getSpace(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("|--");
        for (int x = 0; x < level; x++) {
            sb.insert(0, "| ");
        }
        return sb.toString();
    }

    // 通过过滤器获取文件
    public static void getFiles() {
        File dir = new File("e:\\java0331");
        FilenameFilter filter = (dir1, name) -> name.endsWith(".java");
        List<File> list = new ArrayList<>();
        getFiles(dir, filter, list);
        File destFile = new File(dir, "javalist.txt");
        // write2File(list, destFile);
    }

    // 对指定目录中的内容进行深度遍历，并按照指定过滤器，进行过滤，
    // 将过滤后的内容存储到指定容器List中。
    public static void getFiles(File dir, FilenameFilter filter, List<File> list) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file, filter, list);
            } else {
                //对遍历到的文件进行过滤器的过滤。将符合条件File对象，存储到List集合中。
                if (filter.accept(dir, file.getName())) {
                    list.add(file);
                }
            }
        }
    }

    // 将文件路径写入文件
    public static void write2File(List<File> list, File destFile) {
        BufferedWriter bufw = null;
        try {
            bufw = new BufferedWriter(new FileWriter(destFile));
            for (File file : list) {
                bufw.write(file.getAbsolutePath());
                bufw.newLine();
                bufw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("写入失败");
        } finally {
            if (bufw != null)
                try {
                    bufw.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败");
                }
        }
    }

    public static void main(String[] args) throws IOException {
        /*
         * PrintStream:
         * 1,提供了打印方法可以对多种数据类型值进行打印。并保持数据的表示形式。
         * 2，它不抛IOException.
         *
         * 构造函数，接收三种类型的值：
         * 1，字符串路径。
         * 2，File对象。
         * 3，字节输出流。
         * */
        PrintStream out = new PrintStream("print.txt");
        out.write(610);//只写最低8位，
        out.print(97);//将97先变成字符保持原样将数据打印到目的地。
        out.close();
    }

    public void printWriter() throws IOException {
        /*PrintWriter：字符打印流。
         * 构造函数参数：
         * 1，字符串路径。
         * 2，File对象。
         * 3，字节输出流。
         * 4，字符输出流。*/
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new FileWriter("out.txt"), true);
        String line = null;
        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line))
                break;
            out.println(line.toUpperCase());
            // out.flush();
            out.close();
            bufr.close();
        }
    }

    public void scanner01() {
        // 创建键盘录入对象
        Scanner sc = new Scanner(System.in);
        // next()方法是不接收空格的，在接收到有效数据前，所有的空格或者tab键等输入被忽略，若有有效数据，则遇到这些键退出。
        // nextLine()可以接收空格或者tab键，其输入应该以enter键结束。
        Scanner scan = new Scanner(System.in).useDelimiter("\\s+");//以空格作为分隔符

        // java.class类的编码为：unicode;
        // windows 默认的编码为：中文：gb2312; 英文：iso8859-1;
        String E1 = "[\u4e00-\u9fa5]";//汉字

        // 获取数据
        if (sc.hasNextInt()) {
            int x = sc.nextInt();
            System.out.println("x:" + x);
        } else {
            System.out.println("你输入的数据有误");
        }

        // 先获取一个字符串，再获取一个int值
        int a = sc.nextInt();
        Scanner sc2 = new Scanner(System.in);
        String s = sc2.nextLine();
        System.out.println("a:" + a + ",s:" + s);

        // 获取输入日期
        scan = new Scanner(System.in).useDelimiter("\\D");//匹配非数字
        System.out.print("请输入当前日期（年-月-日）:");
        int year = scan.nextInt();
        int month = scan.nextInt();
        int date = scan.nextInt();
        scan.close();
    }

    // 获取键盘输入
    private static String key_Input() {
        BufferedReader bufrIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufrIn.readLine();
        } catch (IOException e) {
        } finally {
            try {
                bufrIn.close();
            } catch (IOException e) {
            }
        }

        return null;
    }

}
