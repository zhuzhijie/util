package com.jing.test;

import java.lang.reflect.InvocationTargetException;

import com.jing.lang.ReflectUtil;
import com.jing.util.Date;


public class ReflectUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：862990787
	 * Sep 16, 2013 11:48:52 AM
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		try {
//			Class c=Class.forName("net.maxt.test.IPTest");
//			System.out.println(ReflectUtil.invokeStaticMethod(c, "main", new Object[]{"12","你好"}));
			
			//Object re=ReflectUtil.invokeMethod(new Date(), "parseDate", "2013-10-09 10:01:02");
			Class date=Class.forName("net.maxt.util.Date");
			Object re=ReflectUtil.invokeMethod(date, "parseDate", "2013-10-08 10:01:02");
			System.out.println(re);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
