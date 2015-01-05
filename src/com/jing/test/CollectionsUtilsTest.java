package com.jing.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.jing.util.CollectionUtil;


public class CollectionsUtilsTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 2:27:01 PM
	 * @param args
	 */
	public static void main(String[] args) {
//		List<Integer> list=new ArrayList<Integer>();
//		list.add(1);
//		list.add(3);
//		list.add(5);
//		Integer[] a=new Integer[]{3,66};
		//System.out.println(a[0]+" "+a[1]);
		//StringUtils.a();
		//System.out.println(((List<Integer>)CollectionUtil.arrayToList(a)).size());
//		a=CollectionUtil.concatenateArrays(a, new Integer[]{965,333});
//		for(Integer aa : a){
//			System.out.println(aa);
//		}
		Object[] rowObject=null;
		System.out.println(CollectionUtil.isEmpty(rowObject));
		CollectionUtil.addObjectToArray(rowObject, new Double(123));
		
		//System.out.println(CollectionUtil.randomOne(a));
		//System.out.println(CollectionUtil.isEmpty(map));
		//System.out.println(CollectionUtil.isEmpty(list));
		//System.out.println(CollectionUtil.arrayToList(CollectionUtil.listToArray(list)).size());
//		String [] a=new String[]{"4","5","6"};
//		Object [] b=CollectionUtil.reverseArray(a);
//		for(Object bi : b){
//			System.out.println(bi);
//		}
		
//		double [] a=new double[]{0.0,56.0,1.0,23.0,11.0};
//		double [] b=CollectionUtil.sortArray(a);
//		for(double bi : b){
//			System.out.println(bi);
//		}
	}

}
