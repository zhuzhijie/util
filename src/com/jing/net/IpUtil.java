package com.jing.net;

import com.jing.lang.StringUtil;

/**
 * IP地址工具类。
 * @author 朱志杰
 */
public class IpUtil {
	static int IP_ARRAY_LENGTH = 4;

	/**
	 * 功能：将编码过的数字解码为ip地址。
	 * 作者：朱志杰 QQ:862990787
	 * 2014-10-22 下午4:04:12
	 * @param ip ip地址转换后的数字
	 * @return String 若IP编码值不规范或者为空直接返回null.
	 */
	public static String decodeIp(long ip) {
		if (ip <= 0L) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ip >> 24 & 0xFF).append(".").append(ip >> 16 & 0xFF)
				.append(".").append(ip >> 8 & 0xFF).append(".")
				.append(ip & 0xFF);
		return sb.toString();
	}

	/**
	 * 
	 * 功能：将ip地址编码为数字
	 * 作者：朱志杰 QQ:862990787
	 * 2014-10-22 下午4:03:12
	 * @param ipString IP地址，如：192.168.1.2
	 * @return long 若IP地址不规范或者为空直接返回0.
	 */
	public static long encodeIp(String ipString) {
		long ipNumber = 0L;
		if (!StringUtil.isEmpty(ipString)) {
			String[] ipArray = ipString.split("\\.");
			if (ipArray.length == IP_ARRAY_LENGTH) {
				ipNumber = Long.parseLong(ipArray[0]) << 24
						| Long.parseLong(ipArray[1]) << 16
						| Long.parseLong(ipArray[2]) << 8
						| Long.parseLong(ipArray[3]);
			}
		}
		return ipNumber;
	}
}
