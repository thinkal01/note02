package com.note.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MsgUtil {
    private static MessageSource messageSource;
    private static MsgUtil msgUtil = new MsgUtil();

    private static void init() {
        synchronized (msgUtil) {
            if (messageSource != null) {
                return;
            }
            messageSource = (MessageSource) AppContext.get().getBean("messageSource");
        }
    }

    /**
     * 从bean属性文件获取对应的内容，不含参数
     *
     * @param key
     * @return
     */
    public static String getMsg(String key) {
        return MsgUtil.getMsg(key, null, null);
    }

    /**
     * 从bean属性文件获取对应的内容，含参数
     *
     * @param key
     * @param objs
     * @return
     */
    public static String getMsg(String key, Object[] objs) {
        return getMsg(key, objs, null);
    }

    /**
     * 从bean属性文件获取对应的内容， 含地区
     *
     * @param key
     * @param locale
     * @return
     */
    public static String getMsg(String key, Locale locale) {
        return getMsg(key, null, locale);
    }

    /**
     * 从bean属性文件获取对应的内容，含参数， 含地区
     * key不存在对应value时返回key作为value
     *
     * @param key
     * @param objs
     * @param locale
     * @return
     */
    public static String getMsg(String key, Object[] objs, Locale locale) {
        if (messageSource == null) {
            init();
        }
        return messageSource.getMessage(key, objs, key, locale);
    }
}
