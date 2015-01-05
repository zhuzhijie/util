package com.jing.lang;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 异常综合类。
 * @author 朱志杰 QQ：862990787
 * Sep 18, 2013 6:21:54 AM
 */
public class ExceptionUtil {
	
	/**
	 * 功能：得到异常的堆栈信息。包括异常信息简述，以及详细堆栈信息。
	 * @author 朱志杰 QQ：862990787
	 * Sep 18, 2013 6:26:04 AM
	 * @param e 异常
	 * @return List<String>
	 */
	public static List<String> getStackTrace(Exception e){
		List<String> errList=new LinkedList<String>();
		errList.add(e.toString());
		for(StackTraceElement line : e.getStackTrace()){
			errList.add(line.toString());
		}
		return errList;
	}
}
