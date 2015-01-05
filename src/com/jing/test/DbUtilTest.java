package com.jing.test;

import java.sql.SQLException;
import java.util.Random;

import com.jing.util.DBUtil;


public class DbUtilTest {
	private static DBUtil dbUtil=DBUtil.getInstance("com.mysql.jdbc.Driver"
			,"jdbc:mysql://localhost:3306/first?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gbk",
			"root","me","my");
	
	private static DBUtil my=DBUtil.getInstance("com.mysql.jdbc.Driver"
			,"jdbc:mysql://116.255.149.148:3306/mas?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gbk",
			"mas","","my1");
	
	public static DBUtil getDbUtil(){
		return dbUtil;
	}
	
	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Jul 16, 2013 10:37:59 AM
	 * @param args
	 */
	public static void main(String[] args) {
		while(true){
			String sql="select count(*) from sfda";
			try {
				System.out.println(dbUtil.query(sql).get(0));
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			//System.out.println(Connection.TRANSACTION_REPEATABLE_READ+"");
//		System.out.println(MD5Util.getMD5String("123{abc}"));
//		String findSql="select * from stopstation";
//		try {
//			List<Map<String,Object>> stations=dbUtil.query(findSql);
//			int i=0;
//			for(Map<String,Object> station : stations){
//				//String py=StringUtil.getPinYinHeadChar(station.get("countyname").toString());
////				String upSql="update stopstation set pinYinFirst='"+py+"' where stopStationid='"+station.get("stopStationid")+"'";
////				dbUtil.execute(upSql);
////				System.out.println(i++);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Statement myState=new MyStatement();
//		Connection c=dbUtil.getConnection();
//		try {
//			myState = c.createStatement() ;  
//			c.setAutoCommit(true);
//			myState.execute("delete from aabb");
//			System.out.println(c.isClosed());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

}
