package com.note.base;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalFormat01 {

    @Test
    public void test1() {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        // 1
        String format1 = decimalFormat.format(1.4);
        // 2
        String format2 = decimalFormat.format(1.5);
        // 2
        String format3 = decimalFormat.format(1.6);

        decimalFormat = new DecimalFormat("#.#");
        // 1.2
        String format11 = decimalFormat.format(1.25);
        // 1.3
        String format12 = decimalFormat.format(1.26);

        decimalFormat = new DecimalFormat("#.##");
        // 1.21
        String format5 = decimalFormat.format(1.214);
        // 1.22
        String format6 = decimalFormat.format(1.215);
        // 1.22
        String format7 = decimalFormat.format(1.216);
    }

    @Test
    public void test00() {
        DecimalFormat decimalFormat = new DecimalFormat("0");
        // 1
        String format1 = decimalFormat.format(1.4);
        // 2
        String format2 = decimalFormat.format(1.5);
        // 2
        String format3 = decimalFormat.format(1.6);

        decimalFormat = new DecimalFormat("0.0");
        // 1.2
        String format11 = decimalFormat.format(1.25);
        // 1.3
        String format12 = decimalFormat.format(1.26);

        decimalFormat = new DecimalFormat("0.00");
        // 1.21
        String format5 = decimalFormat.format(1.214);
        // 1.22
        String format6 = decimalFormat.format(1.215);
        // 1.22
        String format7 = decimalFormat.format(1.216);
    }

    @Test
    public void test04() {
        DecimalFormat df3 = new DecimalFormat("000.000");
        DecimalFormat df4 = new DecimalFormat("###.###");
        // 012.340
        System.out.println(df3.format(12.34));
        // 12.34
        System.out.println(df4.format(12.34));

        long c = 299792458;//光速
        //显示为科学计数法，四舍五入
        //2.998E8
        System.out.println(new DecimalFormat("#.###E0").format(c));

        //显示为两位整数的科学计数法,四舍五入
        //29.97925E7
        System.out.println(new DecimalFormat("00.#####E0").format(c));

        //每三位以逗号进行分隔。
        //299,792,458
        System.out.println(new DecimalFormat(",###").format(c));
        // 同上
        System.out.println(new DecimalFormat("###,###").format(c));

        // 111,222.346
        format("###,###.###", 111222.34567);

        // 011,222.346￥
        format("000,000.000￥", 11222.34567);

        // 光速大小为每秒299,792,458米
        System.out.println(new DecimalFormat("光速大小为每秒,###米。").format(c));

        // 以百分比方式计数，并四舍五入
        // 34.568%
        format("##.###%", 0.345678);
        // 03.457%
        format("00.###%", 0.0345678);
        // 345.678‰
        format("###.###\u2030", 0.345678);
    }

    @Test
    public void testNumberFormat() {
        NumberFormat nf = NumberFormat.getInstance();    // 得到默认的数字格式化显示
        // 10,000,000
        System.out.println("格式化之后的数字：" + nf.format(10000000));
        // 1,000.345
        System.out.println("格式化之后的数字：" + nf.format(1000.345));
    }

    public void format(String pattern, double value) {
        DecimalFormat df = new DecimalFormat();
        String str = df.format(value);
        System.out.println("使用" + pattern + "格式化数字" + value + "：" + str);
    }

}
