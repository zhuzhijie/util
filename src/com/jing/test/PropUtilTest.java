package com.jing.test;

import java.io.IOException;

import com.jing.io.PropertiesUtil;


public class PropUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PropertiesUtil propUtil=new PropertiesUtil();
			propUtil.insertOrUpdateValue("dbzzjurl", "localhost:8dd88", "testsdfdsfmy");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
