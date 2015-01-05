package com.jing.test;

import com.jing.util.VerifyUtil;

public class VerifyUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Aug 10, 2013 10:54:30 AM
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(VerifyUtil.chkChineseWord("你好"));
		System.out.println(VerifyUtil.chkTextBypattern("123a", VerifyUtil.integer_regexp));
		//System.out.println(VerifyUtil.isNumeric("01a23"));
//		System.out.println(VerifyUtil.chkChineseWord("霓虹啊"));
	}

}
