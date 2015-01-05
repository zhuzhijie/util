package com.jing.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 集合(List,Map,Set)辅助类。
 * @author 朱志杰 QQ：695520848
 * Jun 9, 2013 2:10:06 PM
 */
public class CollectionUtil {
	
	/**
	 * 功能：从List中随机取出一个元素。
	 * @author 朱志杰 QQ：695520848
	 * Aug 20, 2013 10:46:00 PM
	 * @param objs 源List
	 * @return T List的一个元素
	 */
	public static <T> T randomOne(List<T> list){
		if(isEmpty(list)){
			return null;
		}
		return list.get(MathUtil.randomNumber(0, list.size()));
	}
	
	/**
	 * 功能：从数组中随机取出一个元素。
	 * @author 朱志杰 QQ：695520848
	 * Aug 20, 2013 10:46:00 PM
	 * @param objs 源数组
	 * @return T 数组的一个元素
	 */
	public static <T> T randomOne(T[] objs){
		if(isEmpty(objs)){
			return null;
		}
		return objs[MathUtil.randomNumber(0, objs.length)];
	}
	
	/**
	 * 功能：数组中是否存在这个元素。
	 * @author 朱志杰 QQ：862990787
	 * 2013-11-7 上午11:55:45
	 * @param objArr 数组
	 * @param compare 元素
	 * @return 存在返回true，否则返回false。
	 */
	public static <T> boolean arrayContain(T[] objArr,T compare){
		if(isEmpty(objArr)){
			return false;
		}
		for(T obj : objArr){
			if(obj.equals(compare)){
				return true;
			}
		}
		return false;
	}
	

	/**
	 * 功能：向list中添加数组。
	 * @author 朱志杰 QQ：862990787
	 * 2013-11-6 下午02:54:11
	 * @param list List
	 * @param array 数组
	 */
	public static <T> void addArrayToList(List<T> list, T[] array) {
		if (isEmpty(list)) {
			return;
		}
		for (T t : array) {
			list.add(t);
		}
	}
	
	/**
	 * 功能：将数组进行反转，倒置。
	 * @author 朱志杰 QQ：695520848
	 * Aug 20, 2013 10:46:00 PM
	 * @param objs 源数组
	 * @return T[] 反转后的数组
	 */
	public static <T> T[] reverseArray(T[] objs){
		if(isEmpty(objs)){
			return null;
		}
		T[] res=(T[])java.lang.reflect.Array.newInstance(objs[0].getClass(), objs.length);
		//新序号
		int k=0;
		for(int i=objs.length-1 ; i>=0 ; i--){
			res[k++]=objs[i];
		}
		return res;
	}
	
	/**
	 * 功能：将数组转为list。
	 * @author 朱志杰 QQ：695520848
	 * Aug 20, 2013 10:46:00 PM
	 * @param objs 源数组
	 * @return List
	 */
	public static <T> List<T> arrayToList(T[] objs){
		if(isEmpty(objs)){
			return null;
		}
		List<T> list=new LinkedList<T>();
		for(T obj : objs){
			list.add(obj);
		}
		return list;
	}
	
	/**
	 * 功能：将list转为数组。
	 * @author 朱志杰 QQ：695520848
	 * Aug 20, 2013 10:46:00 PM
	 * @param list 源list
	 * @return T[]
	 */
	public static <T> T[] listToArray(List<T> list){
		if(isEmpty(list)){
			return null;
		}
		T[] objs=(T[])java.lang.reflect.Array.newInstance(list.get(0).getClass(), list.size());
		int i=0; //数组下标。
		for(T obj : list){
			objs[i++]=obj;
		}
		return objs;
	}
	
	/**
	 * 将一个字符串数组的内容全部添加到另外一个数组中，并返回一个新数组。
	 * @param array1 第一个数组
	 * @param array2 第二个数组
	 * @return T[] 拼接后的新数组
	 */
	public static <T> T[] concatenateArrays(T[] array1, T[] array2) {
		if (isEmpty(array1)) {
			return array2;
		}
		if (isEmpty(array2)) {
			return array1;
		}
		T[] resArray=(T[])java.lang.reflect.Array.newInstance(array1[0].getClass(), array1.length+array2.length);
		System.arraycopy(array1, 0, resArray, 0, array1.length);
		System.arraycopy(array2, 0, resArray, array1.length, array2.length);
		return resArray;
	}
	
	/**
	 * 将一个object添加到一个数组中，并返回一个新数组。
	 * @param array被添加到的数组
	 * @param object 被添加的object
	 * @return T[] 返回的新数组
	 */
	public static <T> T[] addObjectToArray(T[] array, T obj) {
		//结果数组
		T[] resArray=null;
		if (isEmpty(array)) {
			resArray=(T[])java.lang.reflect.Array.newInstance(obj.getClass(), 1);
			resArray[0]=obj;
			return resArray;
		}
		//原数组不为空时。
		resArray=(T[])java.lang.reflect.Array.newInstance(array[0].getClass(), array.length+1);
		System.arraycopy(array, 0, resArray, 0, array.length);
		resArray[array.length] = obj;
		return resArray;
	}
	
	/**
	 * 功能：判断数组是不是空。（null或者length==0）
	 * @author 朱志杰 QQ：862990787
	 * 2013-11-7 上午09:49:54
	 * @param array 数组
	 * @return boolean 空返回true，否则返回false。
	 */
	public static <T> boolean isEmpty(T[] array) {
		return (array == null || array.length==0);
	}
	
	
	/**
	 * 功能：集合是否为空。如果传入的值为null或者集合不包含元素都认为为空。
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 2:10:49 PM
	 * @param collection 集合
	 * @return boolean 为空返回true，否则返回false。
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * 功能：Map是否为空。如果传入的值为null或者集合不包含元素都认为为空。
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 2:10:49 PM
	 * @param map Map
	 * @return boolean 为空返回true，否则返回false。
	 */
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}
	
}
