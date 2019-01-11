package com.note.common.velocity.install;

import com.note.common.velocity.User;
import org.junit.Test;

import java.io.File;

public class BeanUtilsTest {
    public static void main(String[] args) throws Exception {
        BeanUtilsTest beanUtilTest = new BeanUtilsTest();
        BeanUtils beanUtils = new BeanUtils();
        beanUtilTest.beanTool(beanUtils, User.class);
        // beanUtilTest.beanTool(beanUtils, People.class);
        // beanUtilTest.beanTool(beanUtils, Admin.class);
    }

    /**
     * 根据bean生成相应的文件
     */
    public void beanTool(BeanUtils beanUtils, Class c) throws Exception {
        beanUtils.init(c);
        beanUtils.createBeanDao(c);
        beanUtils.createBeanDaoImpl(c);
        beanUtils.createBeanService(c);
        beanUtils.createBeanServiceImpl(c);
    }

    public static final String BEAN_DAO_IMPL_TEMPLATE_VM_PATH = "vms/beanDaoImpl.vm";

    @Test
    public void testLoadFile() {
        // FileResourceLoader fileResourceLoader = new FileResourceLoader();
        // InputStream resourceStream = fileResourceLoader.getResourceStream("/vms/beanDao.vm");
        File file = new File(".\\vms\\beanDao.vm");
        String absolutePath = file.getAbsolutePath();
        boolean exists = file.exists();
        return;
    }
}
