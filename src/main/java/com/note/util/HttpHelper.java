/*
 * Copyright 2016-2099 the original author or authors.
 */
package com.note.util;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpHelper {

    private int httpConnectionTimeout;
    private int httpReadTimeout;

    public HttpHelper(int httpConnectionTimeout, int httpReadTimeout) {
        this.httpConnectionTimeout = httpConnectionTimeout;
        this.httpReadTimeout = httpReadTimeout;
    }

    public byte[] doGet(String url) throws IOException {
        byte[] entity;

        try {
            entity = get(url);
        } catch (ConnectException e) {
            throw e;
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (UnknownHostException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        return entity;
    }

    public byte[] doPost(String url, Map<String, Object> parameters) throws IOException {
        byte[] entity;

        try {
            entity = this.post(url, toStringParameters(parameters));
        } catch (ConnectException e) {
            throw e;
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (UnknownHostException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        return entity;
    }

    private byte[] post(String url, String parameter) throws IOException {
        byte[] result = null;
        HttpURLConnection conn = null;
        conn = getHttpURLConnection(url);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        PrintWriter out = null;

        try {
            if (parameter != null && !parameter.equals("")) {
                out = new PrintWriter(conn.getOutputStream());
                out.write(parameter);
                out.flush();
            }

            if (conn.getResponseCode() == 200) {
                result = getResponseData(conn);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
            conn.disconnect();
        }

        return result;
    }

    private byte[] get(String url) throws IOException {
        byte[] result = null;
        HttpURLConnection conn = null;
        conn = getHttpURLConnection(url);

        try {
            if (conn.getResponseCode() == 200) {
                result = getResponseData(conn);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            conn.disconnect();
        }

        return result;
    }

    public HttpURLConnection getHttpURLConnection(String url) throws IOException {
        HttpURLConnection conn = null;
        URL u = new URL(url);
        conn = (HttpURLConnection) u.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Connection", "close");
        conn.setReadTimeout(getHttpReadTimeout());
        conn.setConnectTimeout(getHttpConnectionTimeout());
        return conn;
    }

    public byte[] getResponseData(HttpURLConnection conn) throws IOException {
        ByteArrayOutputStream out = null;
        BufferedInputStream bis = null;
        InputStream in = null;

        try {
            in = conn.getInputStream();
            bis = new BufferedInputStream(in);
            byte[] buf = new byte[1024];
            int len = -1;
            out = new ByteArrayOutputStream();

            while ((len = bis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            closeStream(in);
            closeStream(bis);
            closeStream(out);
        }

        return out.toByteArray();
    }

    /**
     * 请求参数url编码
     *
     * @param parameters
     * @return
     */
    public String toStringParameters(Map<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();

        try {
            String split = "&";
            if (parameters != null && !parameters.isEmpty()) {
                Object[] keys = parameters.keySet().toArray();
                int last = keys.length - 1;
                for (int i = 0; i < keys.length; i++) {
                    Object v = parameters.get(keys[i]);
                    if (v instanceof String[]) {
                        String[] arr = (String[]) v;
                        for (int j = 0; j < arr.length; j++) {
                            sb.append(keys[i]).append("=").append(URLEncoder.encode(arr[j] != null ? arr[j] : "", "utf-8"));
                            if (j != arr.length - 1) {
                                sb.append(split);
                            }
                        }
                    } else {
                        sb.append(keys[i]).append("=").append(URLEncoder.encode(v != null ? v.toString() : "", "utf-8"));
                    }
                    if (i != last) {
                        sb.append(split);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void closeStream(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeStream(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getHttpConnectionTimeout() {
        return httpConnectionTimeout;
    }

    public void setHttpConnectionTimeout(int httpConnectionTimeout) {
        this.httpConnectionTimeout = httpConnectionTimeout;
    }

    public int getHttpReadTimeout() {
        return httpReadTimeout;
    }

    public void setHttpReadTimeout(int httpReadTimeout) {
        this.httpReadTimeout = httpReadTimeout;
    }

    static SSLSocketFactory ssf = null;

    static {
        try {
            TrustManager[] tm = {new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            ssf = sslContext.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
