package com.jing.test;

import java.awt.Color;
import java.util.Map;

import com.jing.image.ImageUtil;
import com.jing.net.IpUtil;


public class IPUtilTest {
	
	public static void main(String[] args){
		long en=IpUtil.encodeIp("192.168.1.12");
		System.out.println(en);
		System.out.println(IpUtil.decodeIp(en));
				
	}

	// 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理
	private static long ipToLong(String strIP) {
		int j = 0;

		int i = 0;

		long[] ip = new long[4];

		int position1 = strIP.indexOf(".");

		int position2 = strIP.indexOf(".", position1 + 1);

		int position3 = strIP.indexOf(".", position2 + 1);

		ip[0] = Long.parseLong(strIP.substring(0, position1));

		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));

		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));

		ip[3] = Long.parseLong(strIP.substring(position3 + 1));

		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];

	}

	// 将10进制整数形式转换成127.0.0.1形式的IP地址
	private static String longToIP(long longIP) {

		StringBuffer sb = new StringBuffer("");

		sb.append(String.valueOf(longIP >>> 24));// 直接右移24位

		sb.append(".");// 将高8位置0，然后右移16位

		sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));

		sb.append(".");

		sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));

		sb.append(".");

		sb.append(String.valueOf(longIP & 0x000000FF));

		sb.append(".");

		return sb.toString();
	}

	
	public void doRes(int resCode){
		if(resCode==1){
			//成功
		}
		if(resCode==-1){
			//失败
			//锁定某个用户等----危险代码---。
		}
	}
	
	public void doRes(Map<String,Object> map){
		String userName=map.get("nickName").toString();
		//-------中间省略100行--------//
		//这里记不清这个userName 是用户名还是用户昵称了。
	}

}
