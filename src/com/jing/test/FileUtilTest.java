package com.jing.test;

import java.io.File;
import java.io.IOException;

import com.jing.io.FileUtil;


public class FileUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：862990787
	 * 2013-11-19 下午03:12:05
	 * @param args
	 */
	public static void main(String[] args) {
		//FileUtil.delete(new File("D:/a"));
//		byte[] b=new byte[]{'a','b'};
//		try {
//			FileUtil.save(b, new File("d:/a/b.txt"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			FileUtil.copy(new File("D:/未完成/二代身份证复印件制作软件v3.4脱壳版"), new File("F://abc"), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
