package com.jing.test;

import java.io.IOException;

import com.jing.net.HttpCilentUtil;


public class HttpCilentUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Jul 17, 2013 2:16:04 PM
	 * @param args
	 */
	public static void main(String[] args) {
		HttpCilentUtil client=new HttpCilentUtil();
		try {
			//client.downFile("http://www.51663.net/resources/imgs/logbg.png", "d://a.jpg");
			//String a=client.requestUrl("http://www.51663.net");
			String a=client.requestUrl("http://www.51663.net","POST");
			System.out.println(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
