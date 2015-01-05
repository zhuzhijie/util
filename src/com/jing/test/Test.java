package com.jing.test;

import java.util.Scanner;

import com.jing.lang.ExceptionUtil;
import com.jing.lang.StringUtil;


public class Test {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：862990787
	 * 2014-3-9 下午04:07:05
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(StringUtil.underlineToJavaString("my_abk_l"));
		
		try {
			int a=Integer.parseInt("sdfsdf");
		} catch (NumberFormatException e) {
			//System.out.println(e);
			for(String s : ExceptionUtil.getStackTrace(e)){
				System.out.println(s);
			}
			//e.printStackTrace();
		}
	}

}
