package com.jing.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.jing.util.Date;
import com.jing.util.MathUtil;


public class TestDate {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * May 29, 2013 10:46:27 AM
	 * @param args
	 */
	public static void main(String[] args) {
		Date date=Date.parseDate("2014-10-13 15", "yyyy-MM-dd HH");
		//Date my =Date.convertDate(Calendar.getInstance());
//		date=date.setHourNew(23);
		//System.out.println(date.toLongDate());
		//System.out.println(my.toLongDate());
		//System.out.println(MathUtil.randomNumber(3, 10,new Integer[]{4,5,6,7,8,3}));
		
//		System.out.println("toTimeStamp: "+date.toTimeStamp());
//		System.out.println("toShortDate: "+date.toShortDate());
//		System.out.println("toString(\"yyyy_MM\"): "+date.toString("yyyy_MM"));
//		System.out.println("getDayOfWeekInt: "+date.getDayOfWeekInt());
//		System.out.println("getDayEnd: "+date.getDayEnd());
//		System.out.println("getMonthStart: "+date.getMonthStart());
		//System.out.println("getDayStart: "+date.dayStart());
//		System.out.println("getLongTime: "+date.getLongTime());
//		System.out.println("getShortTime: "+date.getShortTime());
//		System.out.println("getYearInt: "+date.getYearInt());
//		System.out.println("getMonthInt: "+date.getMonthInt());
//		System.out.println("getDayInt: "+date.getDayInt());
//		System.out.println("getHourInt: "+date.getHourInt());
//		System.out.println("getMinuteInt: "+date.getMinuteInt());
		//System.out.println("dayOfWeekInt: "+date.getDay());
		
//		System.out.println("addYears: "+date.addYears(-1).toLongDate());
//		System.out.println("addMonths: "+date.addMonths(-1).toLongDate());
//		System.out.println("addDays: "+date.addDays(1).toLongDate());
//		System.out.println("addHours: "+date.addHours(-1).toLongDate());
//		System.out.println("addMinutes: "+date.addMinutes(-1).toLongDate());
//		System.out.println("addSeconds: "+date.addSeconds(1).toLongDate());
//		System.out.println("addMilliseconds: "+date.addMilliseconds(800).toLongDate());
//		
//		Date nowdate=new Date();
//		System.out.println(nowdate.substract(nowdate.addDays(-1)).totalHours());
//		System.out.println(nowdate.substract(nowdate.addDays(-1)).totalDays());
//		System.out.println(nowdate.substract(nowdate.addDays(-1)).totalMinutes());
//		System.out.println(nowdate.substract(nowdate.addDays(-1)).totalSeconds());
//		System.out.println(nowdate.substract(nowdate.addDays(-1)).totalMilliseconds());
//		
//		Date my=Date.parseDate("2013-05-29 14:12:10");
//		System.out.println(my.hourInt());
//		
//		
//		//计算两个时间相差几分钟
		//Date one=new Date();
		Date two=Date.parseDate("2014-08-27 23:59:59");
		System.out.println(two.getTime());
		System.out.println(new Date().dayEnd().getTime());
	}

}
