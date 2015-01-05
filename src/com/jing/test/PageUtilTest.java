package com.jing.test;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.jing.web.PageUtil;


public class PageUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Aug 1, 2013 1:20:46 PM
	 * @param args
	 */
	public static void main(String[] args) {
		PageUtil pu=new PageUtil();
		//System.out.println(pu.getAjaxPageHtml("changePage", 2, 20, 180));
		System.out.println(pu.getCurrentPageInfo(6, 20, 107));
		//HttpServletRequest req=new Request();
		//System.out.println(pu.getPageHtml("http://oa.maxt.net", 2, 20, 180,1,req));
		//System.out.println(pu.getPageHtml("http://oa.maxt.net", 2, 20, 180,1));
		//System.out.println(pu.getPageHtml("http://oa.maxt.net", 16, 20, 180,2));
//		try {
//			System.out.println(java.net.URLDecoder.decode("http://oa.maxt.net?1=1&a=%E6%9C%B1%E5%BF%97a%E6%9D%B0123&b=%E8%B5%B5%E9%9D%99&currPage=1","utf-8"));
////			System.out.println(java.net.URLEncoder.encode("你好","utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		//}
	}

}
