package com.jing.test;

import java.io.UnsupportedEncodingException;

import com.jing.lang.DESUtil;
import com.jing.util.DBUtil;


public class DesUtilTest {
	private static DBUtil dbUtil= DBUtil.getInstance("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8",
			"root","me","myPool");

	public static DBUtil getDbUtil(){
		return dbUtil;
	}

	/**
	 * 功能：
	 * @author 朱志杰 QQ：862990787
	 * Sep 7, 2013 9:36:00 AM
	 * @param args
	 */
	public static void main(String[] args) {
		DESUtil d=new DESUtil("132131222");
		try {
			System.out.println(d.encrypt("pwd=pwd&paytophone=customNumber&money=money&cmType=cmtypeId&kfnote=补&other="));
			System.out.println(d.decrypt("HrjxCpSWFSIGb+wUDJm0UMKi5RBulcFe60+Cam0XOMYhq88MAe4P36gZjx8VzwUyHrhXPdyoy0+zKZEVpaRyWRLEkoDtci8GuM7jiZ4bhMQ="));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
