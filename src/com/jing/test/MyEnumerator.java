package com.jing.test;

import java.util.Enumeration;

public class MyEnumerator implements Enumeration {

	int count=0; // 计数器
	Object[] dataArray=new Object[]{"a","b"}; // 存储数据数组的引用
    int length=dataArray.length; //存储的数组的长度
    
	@Override
	public boolean hasMoreElements() {
		 return (count< length);
	}

	@Override
	public Object nextElement() {
		 return dataArray[count++];
	}

}
