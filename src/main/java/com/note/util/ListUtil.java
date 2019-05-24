package com.note.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Random;

public class ListUtil {

    /**
     * 随机获取list集合值
     *
     * @param tList
     * @param <T>
     * @return 随机获取的值
     */
    public static <T> T randomValue(List<T> tList) {
        if (CollectionUtils.isEmpty(tList)) {
            return null;
        }
        // return tList.get((int) (Math.random() * tList.size()));
        return tList.get(new Random().nextInt(tList.size()));
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
