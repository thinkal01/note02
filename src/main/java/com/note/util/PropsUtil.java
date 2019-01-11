package com.note.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropsUtil {

    private static Logger LOG = Logger.getLogger(PropsUtil.class);

    // 属性文件一般放到
    // 把db.properties放在系统属性java.home下，一般是jdk目录下的jre目录里。
    // private static final String javaHome = System.getProperty("java.home");
    // private static final String FS = System.getProperty("file.separator");
    // private static final String propsFileName = javaHome + FS +"destinations.properties";

    private static final String propsFileName = "properties/config.properties";

    private static Properties properties = null;

    static {
        properties = new Properties();
        InputStream inputStream = PropsUtil.class.getResourceAsStream("/" + propsFileName);

        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            LOG.error("配置文件加载失败");
        }
    }

    /**
     * 构造函数找到数据源，并用这个数据源创建连接
     */
    public PropsUtil() {

    }

    /**
     * 获取字符串类型值
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public String getPropsFilePath() {
//        /E:/Java/workspace/git/note01/note01/target/classes/
        String filePath = this.getClass().getResource("/").getPath();
//        /E:/Java/workspace/git/note01/note01/target/destinations.properties
        filePath = filePath.substring(0, filePath.indexOf("classes")) + propsFileName;
        return filePath;
    }

    public InputStream getPropsIS() {
        InputStream ins = Object.class.getResourceAsStream("/" + propsFileName);
        return ins;
    }

    /**
     * 读取属性文件中的属性值
     *
     * @param attr
     * @return
     */
    public String readSingleProps(String attr) {
        String retValue = "";
        Properties props = new Properties();
        try {
            if (!FileUtil.isFileExist(getPropsFilePath())) {
                return "";
            }

            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();
            retValue = props.getProperty(attr);
        } catch (Exception e) {
            return "";
        }

        return retValue;
    }

    /**
     * 读取属性文件中的属性值
     */
    public HashMap readAllProps() {
        HashMap h = new HashMap();
        Properties props = new Properties();

        try {
            InputStream fi = getPropsIS();
            props.load(fi);
            fi.close();
            Enumeration er = props.propertyNames();
            while (er.hasMoreElements()) {
                String paramName = (String) er.nextElement();
                h.put(paramName, props.getProperty(paramName));
            }
        } catch (Exception e) {
            return new HashMap();
        }

        return h;
    }

}