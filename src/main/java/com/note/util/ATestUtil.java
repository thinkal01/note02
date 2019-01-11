package com.note.util;

import org.junit.Test;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ATestUtil {

    @Test
    public void test01() {
        String utf8URLencode = CharTools.Utf8URLencode("abc世界123");
        String utf8URLdecode = CharTools.Utf8URLdecode(utf8URLencode);
        String url = "http://www.google.com/search?hl=zh-CN&newwindow=1&q=%E4%B8%AD%E5%9B%BD%E5%A4%A7%E7%99%BE%E7%A7%91%E5%9C%A8%E7%BA%BF%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2&btnG=%E6%90%9C%E7%B4%A2&lr=";
        boolean utf8Url = CharTools.isUtf8Url(url);

        return;
    }

    @Test
    public void test02() {
        String strVal = "This is a dog,big dog!";
        String strResult = CTool.replace(strVal, "dog", "cat");
        return;
    }

    @Test
    public void test03() {
        String s = DealString.convertNumToMoney(5);

        return;
    }

    @Test
    public void testFile() {
        FileUtil.makeDirectory("/a/b/c");

        File[] files = FileUtil.listAll(new File(""), new FileFilter() {
            @Override
            public boolean accept(File f) {
                return true;
            }

            @Override
            public String getDescription() {
                return null;
            }
        });

        URI url = FileUtil.getURI(new File("."));
        return;
    }

    @Test
    public void testProps() {
        String propsFilePath = new PropsUtil().getPropsFilePath();

        return;
    }

    @Test
    public void testMoney() {
        BigDecimal detail_interest = new BigDecimal(125000500.21);
        System.out.println(ArabiaToChinese.change(detail_interest.doubleValue()));
    }

    @Test
    public void testHttpHelper() {
        HttpHelper httpHelper = new HttpHelper(5000, 5000);
        Map parameters = new HashMap();
        parameters.put("p1", "参数1");
        parameters.put("p2", new String[]{"a", "b", "c"});
        parameters.put(12.3, "a");
//      p1=%E5%8F%82%E6%95%B01&p2=a&p2=b&p2=c&12.3=a
        String s = httpHelper.toStringParameters(parameters);
        return;
    }

    @Test
    public void testMd5() {
        MD5Util getMD5 = new MD5Util();
        String s = getMD5.GetMD5Code("654321");
        int length = s.length();
    }

    @Test
    public void testDealStr() {
        String s = new String("abcdefg");
    }
}
