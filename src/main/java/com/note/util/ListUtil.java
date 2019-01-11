package com.note.util;

import java.util.List;

public class ListUtil {

    /**
     * 随机获取list集合值
     *
     * @param tList
     * @param <T>
     * @return 随机获取的值
     */
    public static <T> T randomValue(List<T> tList) {
        return tList.get((int) (Math.random() * tList.size()));
    }

    /**
     * 随机删除list中值
     *
     * @param tList
     * @param <T>
     * @return 被删除的值
     */
    public static <T> T randomRemove(List<T> tList) {
        return tList.remove((int) (Math.random() * tList.size()));
    }

}
