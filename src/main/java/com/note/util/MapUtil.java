package com.note.util;

import java.util.Map;

public class MapUtil {

    /**
     * 获取map第一个实体,一般用于map中只有一个实体时
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return 第一个实体
     */
    public static <K, V> Map.Entry<K, V> getFirstEntry(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            return entry;
        }
        return null;
    }
}
