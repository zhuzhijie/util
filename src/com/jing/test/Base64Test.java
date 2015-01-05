package com.jing.test;

import com.jing.image.ImageUtil;
import com.jing.lang.Base64;
import com.jing.util.MathUtil;

public class Base64Test {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：862990787
	 * 2013-11-19 上午09:38:55
	 * @param args
	 */
	public static void main(String[] args) {
		//Base64.setEncodeKey(".ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=");
		//System.out.println(Base64.decode(Base64.encode("你好啊")));
		//String b=ImageUtil.base64Encode("d://a.jpg");
		//ImageUtil.base64Decode(b, "e:\\46546.jpg");
		int weight=1236596;
		String dun=MathUtil.formatDouble(new Double(weight)/1000, 3);
		System.out.println(dun);
	}

}
