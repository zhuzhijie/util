package com.jing.util;



/**
 * Map集合工具类
 * @author 朱志杰 QQ：862990787
 * Aug 10, 2013 10:30:54 AM
 */
public class MapUtil {
	

	/*// 地球半径 6378.137 公里
	private static double EARTH_RADIUS = 6378.137;
	
	*//**
	 * 根据地球上两点经纬度计算两点的距离，单位为千米。
	 * @param lat1 第一个点的纬度
	 * @param lng1 第一个点的经度
	 * @param lat2 第儿个点的纬度
	 * @param lng2 第二个点的经度
	 * @return 距离单位为千米。
	 *//*
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		// s = Math.round(s * 10000) / 10000;
		return s;
	}

	*//**
	 * 用于计算地球上两点之间的距离。
	 * @param d
	 * @return
	 *//*
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}*/
}
