package com.note.base;

import org.junit.Test;

import java.util.*;

public class Collection01 {

    // 把集合转成数组
    public void test01() {
        Collection c = new ArrayList();
        c.add("java");
        Object[] objs = c.toArray();
    }


}
