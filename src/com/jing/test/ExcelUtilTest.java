package com.jing.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jing.io.ExcelUtil;


public class ExcelUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Jul 30, 2013 5:51:03 PM
	 * @param args
	 */
	public static void main(String[] args) {
		ExcelUtil e=new ExcelUtil("d:\\a.xlsx");
//		e.setAutoColumnWidth(true);
//		List<Object[]> list=new ArrayList<Object[]>();
//		list.add(new Object[]{"1296016895,流水号：68921681超过了62.734375秒交易未完成，直接退出。 ","123456"});
//		list.add(new Object[]{"12 ","123456适当放松放松的方式地方了看见了看士大夫士大夫"});
//		try {
//			e.makeExcel("123", new String[]{"one","two"}, list);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			e.write(0, 2, 3, "你好啊aaa");
			List<Object[]> list=e.read(0);
			for(Object[] objArr : list){
				for(Object obj : objArr){
					System.out.print(obj+" ");
				}
				System.out.println();
			}
			System.out.println(e.read(0, 1, 1));
			System.out.println(e.getSheetLastRowNum(0));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
