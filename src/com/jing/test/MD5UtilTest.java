package com.jing.test;

import java.io.File;
import java.io.IOException;

import com.jing.lang.MD5Util;


public class MD5UtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 1:50:17 PM
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(MD5Util.getMD5String("123"));
		try {
			System.out.println(MD5Util.getFileMD5String(new File("D://2.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
