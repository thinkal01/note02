package com.note.common.velocity.install;

import com.note.util.FileUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanUtils {
    // public static final String BEAN_DAO_TEMPLATE_VM_PATH = FileUtil.getResourcePath("vms/beanDao.vm");
    public static final String BEAN_DAO_TEMPLATE_VM_PATH = "vms/beanDao.vm";
    public static final String BEAN_DAO_IMPL_TEMPLATE_VM_PATH = "vms/beanDaoImpl.vm";
    public static final String BEAN_SERVICE_TEMPLATE_VM_PATH = "vms/beanService.vm";
    public static final String BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH = "vms/beanServiceImpl.vm";

    //文件 地址
    //private static final String BEAN_PATH = "com/b510/base/bean";
    private static final String DAO_PATH = "com/b510/base/dao";
    private static final String DAO_IMPL_PATH = "com/b510/base/dao/impl";
    private static final String SERVICE_PATH = "com/b510/base/service";
    private static final String SERVICE_IMPL_PATH = "com/b510/base/service/impl";

    //包名
    private static final String BEAN_URL = "com.b510.base.bean";
    private static final String DAO_URL = "com.b510.base.dao";
    private static final String DAO_IMPL_URL = "com.b510.base.dao.impl";
    private static final String SERVICE_URL = "com.b510.base.service";
    private static final String SERVICE_IMPL_URL = "com.b510.base.service.impl";

    private static Bean bean = new Bean();
    private static Annotation annotation = new Annotation();

    /**
     * 初始化bean和注解
     */
    public void init(Class c) {
        if (c != null) {
            String cName = c.getName();
            bean.setName(getLastChar(cName));
            bean.setBeanUrl(cName);
            bean.setLowerName(getLowercaseChar(getLastChar(cName)));

            annotation.setAuthorName("hongten");
            annotation.setAuthorMail("hongtenzone@foxmail.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            annotation.setDate(simpleDateFormat.format(new Date()));
            annotation.setVersion("1.0");
        }
    }

    /**
     * 创建bean的Dao
     */
    public void createBeanDao(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + DAO_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Dao.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanDaoUrl(DAO_URL);

        fw.write(createCode(BEAN_DAO_TEMPLATE_VM_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    /**
     * 创建bean的Dao的实现
     */
    public void createBeanDaoImpl(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + DAO_IMPL_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "DaoImpl.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanDaoUrl(DAO_URL);
        bean.setBeanDaoImplUrl(DAO_IMPL_URL);

        fw.write(createCode(BEAN_DAO_IMPL_TEMPLATE_VM_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }


    /**
     * 创建bean的Service
     */
    public void createBeanService(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + SERVICE_PATH + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Service.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanServiceUrl(SERVICE_URL);

        fw.write(createCode(BEAN_SERVICE_TEMPLATE_VM_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    /**
     * 创建bean的Service的实现
     */
    public void createBeanServiceImpl(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + SERVICE_IMPL_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "ServiceImpl.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanDaoUrl(DAO_URL);
        bean.setBeanServiceUrl(SERVICE_URL);
        bean.setBeanServiceImplUrl(SERVICE_IMPL_URL);

        fw.write(createCode(BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH, bean, annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }


    /**
     * 根据模板生成代码
     *
     * @param fileVMPath 模板路径
     * @param bean       目标bean
     * @param annotation 注释
     * @return
     * @throws Exception
     */
    public String createCode(String fileVMPath, Bean bean, Annotation annotation) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(fileVMPath);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("bean", bean);
        velocityContext.put("annotation", annotation);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 创建文件
     *
     * @param file
     */
    public void createFilePath(File file) {
        if (!file.exists()) {
            System.out.println("创建[" + file.getAbsolutePath() + "]情况：" + file.mkdirs());
        } else {
            System.out.println("存在目录：" + file.getAbsolutePath());
        }
    }


    /**
     * 获取路径的最后面字符串<br>
     * 如：<br>
     * <code>str = "com.b510.base.bean.ItemUser"</code><br>
     * <code> return "ItemUser";<code>
     *
     * @param str
     * @return
     */
    public String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }

    /**
     * 把第一个字母变为小写<br>
     * 如：<br>
     * <code>str = "UserDao";</code><br>
     * <code>return "userDao";</code>
     *
     * @param str
     * @return
     */
    public String getLowercaseChar(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 显示信息
     *
     * @param info
     */
    public void showInfo(String info) {
        System.out.println("创建文件：" + info + "成功！");
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
