package com.note.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;

public final class HttpClient {

    private HttpClient() {
    }

    public static String doGet(String url, Map<String, String> params) throws URISyntaxException {
        return service(url, params, new HttpGet());
    }

    public static String doPost(String url, Map<String, String> params) throws URISyntaxException {
        return service(url, params, new HttpPost());
    }

    private static String service(String url, Map<String, String> params, HttpRequestBase hrb) throws URISyntaxException {
        List<NameValuePair> list = new LinkedList<>();
        Set<Entry<String, String>> set = params.entrySet();

        for (Entry<String, String> ety : set) {
            list.add(new BasicNameValuePair(ety.getKey(), ety.getValue()));
        }

        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        ub.setParameters(list);
        hrb.setURI(ub.build());

        return getResult(hrb);
    }

    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                response.close();
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * 使用post请求
     *
     * @param obj
     * @param httpUrl
     * @return
     * @throws Exception
     */
    public static String executePost(Object obj, String httpUrl) throws Exception {
        String result = "";
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        List<BasicNameValuePair> params = new ArrayList<>();

        String fieldName = null;
        String fieldNameUpper = null;
        Method getMethod = null;
        String value = null;

        for (int i = 0; i < fields.length; i++) {
            fieldName = fields[i].getName();
            fieldNameUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            getMethod = cls.getMethod("get" + fieldNameUpper);
            value = (String) getMethod.invoke(obj);
            if (value != null) {
                params.add(new BasicNameValuePair(fieldName, value));
            }
        }

        HttpPost httppost = new HttpPost(httpUrl);
        // 设置参数的编码UTF-8
        httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
        HttpEntity entity = httpclient.execute(httppost).getEntity();

        if (entity != null && entity.getContentLength() != -1) {
            result = EntityUtils.toString(entity);
        }

        httpclient.getConnectionManager().shutdown();
        return result;
    }

    @Test
    public void doGet() throws Exception {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个GET对象
        HttpGet get = new HttpGet("http://www.sogou.com");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);

        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            // if (statusCode == HttpURLConnection.HTTP_OK) {
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
        }

        //关闭httpclient
        response.close();
        httpClient.close();
    }

    // 执行get请求带参数
    @Test
    public void doGetWithParam() throws Exception {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个uri对象
        URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
        uriBuilder.addParameter("query", "花千骨");
        HttpGet get = new HttpGet(uriBuilder.build());
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);

        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");
        //关闭httpclient
        response.close();
        httpClient.close();
    }

    @Test
    public void doPost() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);

        String string = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();
    }

    // 带参数post请求
    @Test
    public void doPostWithParam() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");

        //创建一个Entity。模拟一个表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username", "zhangsan"));
        kvList.add(new BasicNameValuePair("password", "123"));
        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");

        //设置请求的内容
        post.setEntity(entity);

        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String string = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();
    }

    public JSONObject getJSONObjectByGet(String url) {
        JSONObject resultJsonObject = null;

        //创建httpClient连接
        CloseableHttpClient httpClient = HttpClients.createDefault();

        StringBuilder urlStringBuilder = new StringBuilder(url);
        StringBuilder entityStringBuilder = new StringBuilder();
        //利用URL生成一个HttpGet请求
        HttpGet httpGet = new HttpGet(urlStringBuilder.toString());
        // HttpClient 发送Post请求
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //得到httpResponse的状态响应码
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //得到httpResponse的实体数据
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        entityStringBuilder.append(line);
                    }
                    // 从HttpEntity中得到的json String数据转为json
                    String json = entityStringBuilder.toString();
                    resultJsonObject = JSON.parseObject(json);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            //关闭流
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return resultJsonObject;
    }

    /**
     * Post方式 得到JSONObject
     *
     * @param paramsHashMap post参数
     * @param url
     * @param encoding      编码utf-8
     * @return
     */
    public JSONObject getJSONObjectByPost(Map<String, String> paramsHashMap, String url, String encoding) {
        //创建httpClient连接
        CloseableHttpClient httpClient = HttpClients.createDefault();

        JSONObject result = null;
        List<NameValuePair> nameValuePairArrayList = new ArrayList<NameValuePair>();
        // 将传过来的参数添加到List<NameValuePair>中
        if (paramsHashMap != null && !paramsHashMap.isEmpty()) {
            //遍历map
            for (Map.Entry<String, String> entry : paramsHashMap.entrySet()) {
                nameValuePairArrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        UrlEncodedFormEntity entity = null;
        try {
            // 利用List<NameValuePair>生成Post请求的实体数据
            // UrlEncodedFormEntity 把输入数据编码成合适的内容
            entity = new UrlEncodedFormEntity(nameValuePairArrayList, encoding);
            HttpPost httpPost = new HttpPost(url);
            // 为HttpPost设置实体数据
            httpPost.setEntity(entity);
            // HttpClient 发送Post请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 10 * 1024);
                        StringBuilder strBuilder = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            strBuilder.append(line);
                        }
                        // 用fastjson的JSON将返回json字符串转为json对象
                        result = JSON.parseObject(strBuilder.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                //关闭流
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * post+json 请求
     * @param url
     * @param json
     */
    public static JSONObject doPost(String url, JSONObject json) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;

        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
