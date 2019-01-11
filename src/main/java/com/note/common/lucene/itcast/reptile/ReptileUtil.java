package com.note.common.lucene.itcast.reptile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 爬虫工具.....
 */
public class ReptileUtil {
    public static String sendGet(String url, String param, String charset) {
        String result = "";
        try {
            String urlName = url + "?" + param;//
            URL U = new URL(urlName);
            URLConnection connection = U.openConnection();
            connection.connect();
            InputStreamReader isr = null;
            if (IsNotEmpty(charset)) {
                isr = new InputStreamReader(connection.getInputStream(), charset);
            } else {
                isr = new InputStreamReader(connection.getInputStream());
            }
            BufferedReader in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error occur:" + e);
        }
        return result;
    }

    public static String sendPost(String url, String param, String charset) {
        String result = "";
        try {
            URL httpurl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("POST");
            PrintWriter out = new PrintWriter(httpConn.getOutputStream());
            out.write(param);
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();
        } catch (Exception e) {

        }
        return result;
    }

    public static boolean IsNotEmpty(Object object) {
        if (null == object || "".equals(object.toString().trim())) {
            return false;
        } else {
            try {
                if (Integer.parseInt(object.toString()) < 1) {
                    return false;
                } else {
                    return true;
                }
            } catch (NumberFormatException e) {
                return true;
            }
        }
    }

}
