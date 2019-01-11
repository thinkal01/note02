package com.note.old.web;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtils {
    private static String baseName = "res";
    private static Locale locale;

    public static String getText(String key) {
        return ResourceBundle.getBundle(baseName, locale).getString(key);
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        MessageUtils.locale = locale;
    }
}