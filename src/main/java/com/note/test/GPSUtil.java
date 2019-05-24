package com.note.test;

import javafx.geometry.Point2D;

/**
 * 计算两个经纬度之间的距离
 */
public class GPSUtil {
    private static final double EARTH_RADIUS = 6378137;// 赤道半径(单位m)

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下（单位m）
     * @param lat1 第一个纬度
     * @param lng1 第一个经度
     * @param lat2 第二个纬度
     * @param lng2 第二个经度
     * @return 两个经纬度的距离
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        // 四舍五入
        s = Math.round(s);
        // s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String[] args) {
        System.out.println(GPSUtil.getDistance(22.75424, 112.76535, 23.014171, 113.10111));
    }

    /**(米勒投影算法）将经纬度转化为平面坐标 （单位m）
     * @param lon 经度
     * @param lat 维度
     */
    public static Point2D MillierConvertion(double lon, double lat) {
        double L = 6381372 * Math.PI * 2;// 地球周长
        double W = L;// 平面展开后，x轴等于周长
        double H = L / 2;// y轴约等于周长一半
        double mill = 2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间
        double x = lon * Math.PI / 180;// 将经度从度数转换为弧度
        double y = lat * Math.PI / 180;// 将纬度从度数转换为弧度
        y = 1.25 * Math.log(Math.tan(0.25 * Math.PI + 0.4 * y));// 米勒投影的转换
        // 弧度转为实际距离
        x = (W / 2) + (W / (2 * Math.PI)) * x;
        y = (H / 2) - (H / (2 * mill)) * y;
        // double[] result = new double[2];
        // result[0] = x;
        // result[1] = y;
        return new Point2D(x, y);
    }
}