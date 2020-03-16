package com.note.util;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.rmi.dgc.Lease;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 此类中封装一些常用的文件操作。
 */
public class FileUtil {
    private static Logger log = Logger.getLogger(MD5Encrypt.class);

    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    /*private FileUtil() {
    }*/

    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     *
     * @param file 需要修改最后访问时间的文件。
     */
    public static void touch(File file) {
        long currentTime = System.currentTimeMillis();

        if (!file.exists()) {
            System.err.println("file not found:" + file.getName());
            System.err.println("Create a new file:" + file.getName());
            try {
                if (file.createNewFile()) {
                    System.out.println("Succeeded!");
                } else {
                    System.err.println("Create file failed!");
                }
            } catch (IOException e) {
                System.err.println("Create file failed!");
                e.printStackTrace();
            }
        }

        boolean result = file.setLastModified(currentTime);
        if (!result) {
            System.err.println("touch failed: " + file.getName());
        }
    }



    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     *
     * @param fileName 需要修改最后访问时间的文件的文件名。
     * @since 1.0
     */
    public static void touch(String fileName) {
        File file = new File(fileName);
        touch(file);
    }

    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     *
     * @param files 需要修改最后访问时间的文件数组。
     * @since 1.0
     */
    public static void touch(File[] files) {
        for (int i = 0; i < files.length; i++) {
            touch(files[i]);
        }
    }

    /**
     * 修改文件的最后访问时间。
     * 如果文件不存在则创建该文件。
     *
     * @param fileNames 需要修改最后访问时间的文件名数组。
     * @since 1.0
     */
    public static void touch(String[] fileNames) {
        File[] files = new File[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            files[i] = new File(fileNames[i]);
        }

        touch(files);
    }

    /**
     * 创建文件
     *
     * @param fileString 文件字符串
     * @param filepath   文件路径
     * @param filename   文件名称
     */
    public static void writeFileString(String fileString, String filepath, String filename) {
        File f = new File(filepath + File.separator + filename);

        try {
            f.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(f, true));
            output.write(fileString);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断指定的文件是否存在。
     *
     * @param fileName 要判断的文件的文件名
     * @return 存在时返回true，否则返回false。
     * @since 1.0
     */
    public static boolean isFileExist(String fileName) {
        return new File(fileName).isFile();
    }

    /**
     * 创建指定的目录。
     * 如果指定的目录的父目录不存在则创建其所有需要的父目录。
     * <b>注意：可能会在返回false的时候创建部分父目录。</b>
     *
     * @param file 要创建的目录
     * @return 完全创建成功时返回true，否则返回false。
     * @since 1.0
     */
    public static boolean makeDirectory(File file) {
        File parent = file.getParentFile();
        if (parent != null) {
            return parent.mkdirs();
        }

        return false;
    }

    /**
     * 创建指定的目录。
     * 如果指定的目录的父目录不存在则创建其所有需要的父目录。
     * <b>注意：可能会在返回false的时候创建部分父目录。</b>
     *
     * @param fileName 要创建的目录的目录名
     * @return 完全创建成功时返回true，否则返回false。
     * @since 1.0
     */
    public static boolean makeDirectory(String fileName) {
        File file = new File(fileName);
        return makeDirectory(file);
    }

    public static void createDir(String dir, boolean ignoreIfExitst) throws IOException {
        File file = new File(dir);

        if (ignoreIfExitst && file.exists()) {
            return;
        }

        if (file.mkdir() == false) {
            throw new IOException("Cannot create the directory = " + dir);
        }
    }

    public static String createDir(String destPath, String dir) {
        File file = new File(destPath + File.separator + dir);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + File.separator;
    }

    public static void createDirs(String dir, boolean ignoreIfExitst) throws IOException {
        File file = new File(dir);
        if (ignoreIfExitst && file.exists()) {
            return;
        }
        if (file.mkdirs() == false) {
            throw new IOException("Cannot create directories = " + dir);
        }
    }

    /**
     * 清空指定目录中的文件。
     * 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
     * 另外这个方法不会迭代删除，即不会删除子目录及其内容。
     *
     * @param directory 要清空的目录
     * @return 目录下的所有文件都被成功删除时返回true，否则返回false.
     * @since 1.0
     */
    public static boolean emptyDirectory(File directory) {
        boolean result = true;

        File[] entries = directory.listFiles();
        for (int i = 0; i < entries.length; i++) {
            if (!entries[i].delete()) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 清空指定目录中的文件。
     * 这个方法将尽可能删除所有的文件，但是只要有一个文件没有被删除都会返回false。
     * 另外这个方法不会迭代删除，即不会删除子目录及其内容。
     *
     * @param directoryName 要清空的目录的目录名
     * @return 目录下的所有文件都被成功删除时返回true，否则返回false。
     * @since 1.0
     */
    public static boolean emptyDirectory(String directoryName) {
        File dir = new File(directoryName);
        return emptyDirectory(dir);
    }

    /**
     * 递归删除指定目录及其中的所有内容。
     *
     * @param dir 要删除的目录
     * @return 删除成功时返回true，否则返回false。
     * @since 1.0
     */
    public static boolean deleteDirectory(File dir) {
        if ((dir == null) || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument " + dir + " is not a directory. ");
        }

        File[] entries = dir.listFiles();
        int sz = entries.length;

        for (int i = 0; i < sz; i++) {
            if (entries[i].isDirectory()) {
                if (!deleteDirectory(entries[i])) {
                    return false;
                }
            } else {
                if (!entries[i].delete()) {
                    return false;
                }
            }
        }

        if (!dir.delete()) {
            return false;
        }
        return true;
    }

    /**
     * 递归删除指定目录及其中的所有内容。
     *
     * @param dirName 要删除的目录的目录名
     * @return 删除成功时返回true，否则返回false。
     */
    public static boolean deleteDirectory(String dirName) {
        return deleteDirectory(new File(dirName));
    }

    public static void deleteFile(String filename) throws IOException {
        File file = new File(filename);

        if (file.isDirectory()) {
            throw new IOException("IOException -> BadInputException: not a file.");
        }
        if (file.exists() == false) {
            return;
        }
        if (file.delete() == false) {
            throw new IOException("Cannot delete file. filename = " + filename);
        }
    }

    /**
     * 递归列出目录中的所有内容，包括其子目录中的内容。
     *
     * @param file   要列出的目录
     * @param filter 过滤器
     * @return 目录内容的文件数组。
     */
    public static File[] listAll(File file, FileFilter filter) {
        ArrayList list = new ArrayList();
        File[] files;

        if (!file.exists() || file.isFile()) {
            return null;
        }

        list(list, file, filter);
        files = new File[list.size()];
        list.toArray(files);

        return files;
    }

    /**
     * 将目录中的内容添加到列表。
     *
     * @param list   文件列表
     * @param filter 过滤器
     * @param file   目录
     */
    private static void list(ArrayList list, File file, FileFilter filter) {
        if (filter.accept(file)) {
            list.add(file);
            if (file.isFile()) {
                return;
            }
        }

        if (file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                list(list, files[i], filter);
            }
        }

    }

    /**
     * 返回文件的URL地址。
     *
     * @param file 文件
     * @return 文件对应的的URL地址
     * @throws MalformedURLException
     * @since 1.0
     * @deprecated File类本身带一个toURI方法
     */
    public static URI getURI(File file) {
        URI uri = file.toURI();

        /*String fileURL = "file:/" + file.getAbsolutePath();
        URL url = null;
        try {
            url = new URL(fileURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        return uri;
    }

    /**
     * 从文件路径得到文件名。
     *
     * @param filePath 文件的路径，可以是相对路径也可以是绝对路径
     * @return 对应的文件名
     * @since 1.0
     */
    public static String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

    /**
     * 从文件名得到文件绝对路径。
     *
     * @param fileName 文件名
     * @return 对应的文件路径
     * @since 1.0
     */
    public static String getFilePath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    /**
     * 将DOS/Windows格式的路径转换为UNIX/Linux格式的路径。
     * 其实就是将路径中的"\"全部换为"/"，因为在某些情况下我们转换为这种方式比较方便，
     * 某中程度上说"/"比"\"更适合作为路径分隔符，而且DOS/Windows也将它当作路径分隔符。
     *
     * @param filePath 转换前的路径
     * @return 转换后的路径
     * @since 1.0
     */
    public static String toUNIXpath(String filePath) {
        return filePath.replace('\\', '/');
    }

    /**
     * 从文件名得到UNIX风格的文件绝对路径。
     *
     * @param fileName 文件名
     * @return 对应的UNIX风格的文件路径
     * @see #toUNIXpath(String filePath) toUNIXpath
     * @since 1.0
     */
    public static String getUNIXfilePath(String fileName) {
        File file = new File(fileName);
        return toUNIXpath(file.getAbsolutePath());
    }

    /**
     * 得到文件的类型。
     * 实际上就是得到文件名中最后一个“.”后面的部分。
     *
     * @param fileName 文件名
     * @return 文件名中的类型部分
     * @since 1.0
     */
    public static String getTypePart(String fileName) {
        int point = fileName.lastIndexOf('.');
        int length = fileName.length();

        if (point == -1 || point == length - 1) {
            return "";
        } else {
            return fileName.substring(point + 1, length);
        }
    }

    /**
     * 得到文件的类型。
     * 实际上就是得到文件名中最后一个“.”后面的部分。
     *
     * @param file 文件
     * @return 文件名中的类型部分
     * @since 1.0
     */
    public static String getFileType(File file) {
        return getTypePart(file.getName());
    }

    /**
     * 得到文件名,包括后缀。
     * 实际上就是路径中的最后一个路径分隔符后的部分。
     *
     * @param fileName 文件名
     * @return 文件名中的名字部分
     * @since 1.0
     */
    public static String getNamePart(String fileName) {
        int point = getPathLastIndex(fileName);
        int length = fileName.length();

        if (point == -1) {
            return fileName;
        } else if (point == length - 1) {
            int secondPoint = getPathLastIndex(fileName, point - 1);

            if (secondPoint == -1) {
                if (length == 1) {
                    return fileName;
                } else {
                    return fileName.substring(0, point);
                }
            } else {
                return fileName.substring(secondPoint + 1, point);
            }
        } else {
            return fileName.substring(point + 1);
        }
    }

    /**
     * 得到文件名中的父路径部分。
     * 对两种路径分隔符都有效。
     * 不存在时返回""。
     * 如果文件名是以路径分隔符结尾的则不考虑该分隔符，例如"/path/"返回""。
     *
     * @param fileName 文件名
     * @return 父路径，不存在或者已经是父目录时返回""
     * @since 1.0
     */
    public static String getPathPart(String fileName) {
        int point = getPathLastIndex(fileName);
        int length = fileName.length();

        if (point == -1) {
            return "";
        } else if (point == length - 1) {
            int secondPoint = getPathLastIndex(fileName, point - 1);
            if (secondPoint == -1) {
                return "";
            } else {
                return fileName.substring(0, secondPoint);
            }
        } else {
            return fileName.substring(0, point);
        }
    }

    /**
     * 得到路径分隔符在文件路径中首次出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中首次出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathIndex(String fileName) {
        return getPathIndex(fileName, 0);
    }

    /**
     * 得到路径分隔符在文件路径中指定位置后首次出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的位置
     * @return 路径分隔符在路径中指定位置后首次出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathIndex(String fileName, int fromIndex) {
        int point = fileName.indexOf('/', fromIndex);
        if (point == -1) {
            point = fileName.indexOf('\\', fromIndex);
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中最后出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathLastIndex(String fileName) {
        return getPathLastIndex(fileName, fileName.length() - 1);
    }

    /**
     * 得到路径分隔符在文件路径中指定位置前最后出现的位置。
     * 对于DOS或者UNIX风格的分隔符都可以。
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的位置
     * @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathLastIndex(String fileName, int fromIndex) {
        int point = fileName.lastIndexOf('/', fromIndex);

        if (point == -1) {
            point = fileName.lastIndexOf('\\', fromIndex);
        }

        return point;
    }

    /**
     * 将文件名中的类型部分去掉。
     *
     * @param filename 文件名
     * @return 去掉类型部分的结果
     * @since 1.0
     */
    public static String trimType(String filename) {
        // filename.split("\\.")[0]
        int index = filename.lastIndexOf(".");

        if (index != -1) {
            return filename.substring(0, index);
        } else {
            return filename;
        }
    }

    /**
     * 得到相对路径。
     * 文件名不是目录名的子节点时返回文件名。
     *
     * @param pathName 目录名
     * @param fileName 文件名
     * @return 得到文件名相对于目录名的相对路径，目录下不存在该文件时返回文件名
     * @since 1.0
     */
    public static String getSubpath(String pathName, String fileName) {
        int index = fileName.indexOf(pathName);

        if (index != -1) {
            return fileName.substring(index + pathName.length() + 1);
        } else {
            return fileName;
        }
    }

    /**
     * 检查给定目录的存在性
     * 保证指定的路径可用，如果指定的路径不存在，那么建立该路径，可以为多级路径
     *
     * @param path
     * @return 真假值
     * @since 1.0
     */
    public static final boolean pathValidate(String path) {
        String[] arrayPath = path.split("/");
        String tmpPath = "";

        for (int i = 0; i < arrayPath.length; i++) {
            tmpPath += "/" + arrayPath[i];
            File d = new File(tmpPath.substring(1));
            if (!d.exists()) { //检查Sub目录是否存在
                if (!d.mkdir()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 读取文件的内容
     * 读取指定文件的内容
     *
     * @param path 为要读取文件的绝对路径
     * @return 以行读取文件后的内容。
     * @since 1.0
     */
    public static final String getFileContent(String path) {
        StringBuilder stringBuilder = null;
        BufferedReader br = null;
        FileReader fr = null;

        try {
            File f = new File(path);
            if (f.exists()) {
                fr = new FileReader(path);
                br = new BufferedReader(fr);
                stringBuilder = new StringBuilder();
                String line = br.readLine();
                //判断读取到的字符串是否不为空
                while (line != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(CommonUtil.NEW_LINE);
                    line = br.readLine(); //从文件中继续读取一行数据
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    public static byte[] getFileContent(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            byte[] bt = new byte[4096];
            int cnt = 0;
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new ByteArrayOutputStream();
            while ((cnt = bis.read(bt)) > 0) {
                bos.write(bt, 0, cnt);
            }
            bos.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
        return bos.toByteArray();
    }

    public static byte[] readFile(File upload) {
        InputStream in = null;
        byte[] b = new byte[1280]; //临时存储数组
        byte[] last = new byte[(int) upload.length()]; //存放证书数组

        try {
            in = new BufferedInputStream(new FileInputStream(upload));
            int len = 0;
            int destLen = 0;

            while ((len = in.read(b)) != -1) {
                System.arraycopy(b, 0, last, destLen, len);
                destLen += len;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        return last;
    }

    /**
     * 根据内容生成文件
     *
     * @param path          要生成文件的绝对路径
     * @param moduleContent 文件的内容
     */
    public static final boolean genModuleTpl(String path, String moduleContent) {
        path = getUNIXfilePath(path);
        String[] pathArray = path.split("\\/");
        String modulePath = "";

        for (int i = 0; i < pathArray.length - 1; i++) {
            modulePath += "/" + pathArray[i];
        }

        modulePath = modulePath.substring(1);
        File d = new File(modulePath);
        if (!d.exists()) {
            if (!pathValidate(modulePath)) {
                return false;
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write(moduleContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * 获取图片文件的扩展名（发布系统专用）
     *
     * @param picPath 图片名称加上前面的路径不包括扩展名
     * @return 图片的扩展名
     * @since 1.0
     */
    public static final String getPicExtendName(String picPath) {
        picPath = getUNIXfilePath(picPath);
        String pic_extend = "";

        if (isFileExist(picPath + ".gif")) {
            pic_extend = ".gif";
        }
        if (isFileExist(picPath + ".jpeg")) {
            pic_extend = ".jpeg";
        }
        if (isFileExist(picPath + ".jpg")) {
            pic_extend = ".jpg";
        }
        if (isFileExist(picPath + ".png")) {
            pic_extend = ".png";
        }

        return pic_extend;
    }

    public static void copyFile(File srcfile, File destfile) {
        int cnt = 0;
        byte[] temp = new byte[2048];
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcfile));
            bos = new BufferedOutputStream(new FileOutputStream(destfile));
            while ((cnt = bis.read(temp)) > 0) {
                bos.write(temp, 0, cnt);
            }
            bos.flush();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    //拷贝文件
    public static final boolean CopyFile(File in, File out) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(in);
            fos = new FileOutputStream(out);
            byte[] buf = new byte[1024];
            int i = 0;

            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //拷贝文件
    public static final boolean CopyFile(String infile, String outfile) throws Exception {
        File in = new File(infile);
        File out = new File(outfile);
        return CopyFile(in, out);
    }

    /**
     * 计算图片数量
     *
     * @param id
     * @param dtime
     * @return
     */
    public static final int countPics(String id, String dtime, String extensions) {
        int counts = 0;
        MyFileFilter mfilter = new MyFileFilter(extensions.split(","));
        PropsUtil pu = new PropsUtil();
        String PICROOT = pu.readSingleProps("DestinationsPICROOT").trim();
        String path = PICROOT + "/" + dtime.substring(0, 10) + "/";
        File lfile = new File(path);
        String filename;

        if (lfile.isDirectory()) {
            File[] files = lfile.listFiles(mfilter);
            for (int i = 0; i < files.length; i++) {
                filename = files[i].getName();
                if ((filename.indexOf(id + "_") == 0) && (filename.indexOf("_small") > -1))
                    counts++;
            }
        }

        return counts;
    }

    // 获取资源目录
    public static String getResourcePath(String fileName) {
        URL resource = Object.class.getResource("/" + fileName);
        // File file = new File("");
        // String property = System.getProperty("user.dir");
        String path = resource.getPath();
        return path;
    }

    // 获取clazz类包下资源文件
    public static String getCurrentPath(String fileName, Class clazz) {
        String classFilePath = clazz.getResource("").getPath();
        return classFilePath + "/" + fileName;
    }

    // 获取clazz类包下资源文件
    public static InputStream getCurrentResource(String fileName, Class clazz) {
        InputStream inputStream = clazz.getResourceAsStream(fileName);
        return inputStream;
    }

    @Test
    public void test() {
        File file = new File(".");
        // .
        System.out.println(file.getPath());
        // E:\ItemUser\Idea\note02\.
        System.out.println(file.getAbsolutePath());

        String property = System.getProperty("user.dir");
        // E:\ItemUser\Idea\note02
        System.out.println(property);
    }

}