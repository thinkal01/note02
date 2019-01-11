package com.note.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 通用工具类
 */
public class CommonUtil {

    private static final ThreadLocal<Long> THREAD_USER_ID = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletRequest> THREAD_REQUEST = new ThreadLocal<>();
    private static final String IMG_PATH = PropsUtil.getString("imgPath") + "/";
    private static String server = PropsUtil.getString("imgServer");
    private static final Map<String, Long> IP_ACCESSTIME = new HashMap<>();
    private static long resubmitIntervalTime = Long.parseLong(PropsUtil.getString("resubmitIntervalTime"));
    // 跨平台换行符
    public static final String NEW_LINE = System.getProperty("line.separator");
    // 客服热线
    public static final String CUSTOMER_SERVICE_HOTLINE = PropsUtil.getString("customerServiceHotline");

    /**
     * 返回一个uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 获取userId
     */
    public static Long getUserId() {
        return THREAD_USER_ID.get();
    }

    /**
     * 设置userId
     */
    public static void setUserId(Long userId) {
        THREAD_USER_ID.set(userId);
    }

    /**
     * 移除userId
     */
    public static void removeUserId() {
        THREAD_USER_ID.remove();
    }

    public static void setRequest(HttpServletRequest request) {
        THREAD_REQUEST.set(request);
    }

    public static HttpServletRequest getRequest() {
//        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        HttpServletRequest request = ra.getRequest();
        return THREAD_REQUEST.get();
    }

    public static void removeRequest() {
        THREAD_REQUEST.remove();
    }

    /**
     * 判断是否为重复提交
     */
    public static boolean isResubmit(HttpServletRequest request) {
        String ipAddress = getIpAddress(request);
        Long accessTime = IP_ACCESSTIME.get(ipAddress);

        long currentTimeMillis = System.currentTimeMillis();
        IP_ACCESSTIME.put(ipAddress, currentTimeMillis);

        if (accessTime == null) {
            return false;
        }
        long diff = currentTimeMillis - accessTime;
        return diff < resubmitIntervalTime;
    }

    /**
     * 移除IpAccessTime
     */
    public static void removeIpAccessTime(HttpServletRequest request) {
        IP_ACCESSTIME.remove(getIpAddress(request));
    }

    /**
     * @return
     * @throws IllegalStateException 当前线程不是web请求抛出此异常.
     * @Title: getCurrentRequest
     * @Description: 获取当前request
     *//*
    public static HttpServletRequest getCurrentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }

        return attrs.getRequest();
    }*/
    public static String getSendUrl(HttpServletRequest request) {
        return request.getServletPath();
    }

    public static String getServer(ServletRequest request) {
        if (StringUtils.isBlank(server)) {
            server = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        }
        return server;
    }

    public static String getImgServer(ServletRequest request) {
        server = getServer(request);
        return server + IMG_PATH;
    }

    public static String getImgServer() {
        return getImgServer(getRequest());
    }

    public static AppResult getEmptyObjResult(AppResult result) {
        result.setData(new Serializable() {
        });
        return result;
    }

    // 获取nginx客户端真实ip地址
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    // 根据文件名获取文件类型(下载文件使用),jdk1.7及以上
    public static String getContentType(String filename) {
        String type = null;
        Path path = Paths.get(filename);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }
}