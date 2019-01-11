package com.note.old.web;

import org.apache.taglibs.standard.tag.common.core.Util;

import javax.servlet.jsp.JspTagException;
import java.lang.reflect.Array;
import java.util.*;

public class Functions {

    public Functions() {
    }

    public static String toUpperCase(String input) {
        return input.toUpperCase();
    }

    public static String toLowerCase(String input) {
        return input.toLowerCase();
    }

    public static int indexOf(String input, String substring) {
        if (input == null)
            input = "";
        if (substring == null)
            substring = "";
        return input.indexOf(substring);
    }

    public static boolean contains(String input, String substring) {
        return indexOf(input, substring) != -1;
    }

    public static boolean containsIgnoreCase(String input, String substring) {
        if (input == null)
            input = "";
        if (substring == null)
            substring = "";
        String inputUC = input.toUpperCase();
        String substringUC = substring.toUpperCase();
        return indexOf(inputUC, substringUC) != -1;
    }

    public static boolean startsWith(String input, String substring) {
        if (input == null)
            input = "";
        if (substring == null)
            substring = "";
        return input.startsWith(substring);
    }

    public static boolean endsWith(String input, String substring) {
        if (input == null)
            input = "";
        if (substring == null)
            substring = "";
        int index = input.indexOf(substring);
        if (index == -1)
            return false;
        if (index == 0 && substring.length() == 0)
            return true;
        else
            return index == input.length() - substring.length();
    }

    public static String substring(String input, int beginIndex, int endIndex) {
        if (input == null)
            input = "";
        if (beginIndex >= input.length())
            return "";
        if (beginIndex < 0)
            beginIndex = 0;
        if (endIndex < 0 || endIndex > input.length())
            endIndex = input.length();
        if (endIndex < beginIndex)
            return "";
        else
            return input.substring(beginIndex, endIndex);
    }

    public static String substringAfter(String input, String substring) {
        if (input == null)
            input = "";
        if (input.length() == 0)
            return "";
        if (substring == null)
            substring = "";
        if (substring.length() == 0)
            return input;
        int index = input.indexOf(substring);
        if (index == -1)
            return "";
        else
            return input.substring(index + substring.length());
    }

    public static String substringBefore(String input, String substring) {
        if (input == null)
            input = "";
        if (input.length() == 0)
            return "";
        if (substring == null)
            substring = "";
        if (substring.length() == 0)
            return "";
        int index = input.indexOf(substring);
        if (index == -1)
            return "";
        else
            return input.substring(0, index);
    }

    public static String escapeXml(String input) {
        if (input == null)
            return "";
        else
            return Util.escapeXml(input);
    }

    public static String trim(String input) {
        if (input == null)
            return "";
        else
            return input.trim();
    }

    public static String replace(String input, String substringBefore, String substringAfter) {
        if (input == null)
            input = "";
        if (input.length() == 0)
            return "";
        if (substringBefore == null)
            substringBefore = "";
        if (substringBefore.length() == 0)
            return input;
        StringBuffer buf = new StringBuffer(input.length());
        int startIndex;
        int index;
        for (startIndex = 0; (index = input.indexOf(substringBefore, startIndex)) != -1; startIndex = index + substringBefore.length())
            buf.append(input.substring(startIndex, index)).append(substringAfter);

        return buf.append(input.substring(startIndex)).toString();
    }

    public static String[] split(String input, String delimiters) {
        if (input == null)
            input = "";
        String array[];
        if (input.length() == 0) {
            array = new String[1];
            array[0] = "";
            return array;
        }
        if (delimiters == null)
            delimiters = "";
        StringTokenizer tok = new StringTokenizer(input, delimiters);
        int count = tok.countTokens();
        array = new String[count];
        int i = 0;
        while (tok.hasMoreTokens())
            array[i++] = tok.nextToken();
        return array;
    }

    public static int length(Object obj) throws JspTagException {
        int count;
        if (obj == null)
            return 0;
        if (obj instanceof String)
            return ((String) obj).length();
        if (obj instanceof Collection)
            return ((Collection) obj).size();
        if (obj instanceof Map)
            return ((Map) obj).size();
        count = 0;
        if (obj instanceof Iterator) {
            Iterator iter = (Iterator) obj;
            count = 0;
            for (; iter.hasNext(); iter.next())
                count++;

            return count;
        }
        if (obj instanceof Enumeration) {
            Enumeration enum_ = (Enumeration) obj;
            count = 0;
            for (; enum_.hasMoreElements(); enum_.nextElement())
                count++;

            return count;
        }
        count = Array.getLength(obj);
        return count;
        // throw new JspTagException(Resources.getMessage("FOREACH_BAD_ITEMS"));
    }

    public static String join(String array[], String separator) {
        if (array == null)
            return "";
        if (separator == null)
            separator = "";
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buf.append(array[i]);
            if (i < array.length - 1)
                buf.append(separator);
        }

        return buf.toString();
    }
}