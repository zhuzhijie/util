package com.jing.test;

import java.io.IOException;
import java.util.List;

import com.jing.io.TextUtil;
import com.jing.lang.StringUtil;


public class TextUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * Jun 21, 2013 1:34:32 PM
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//System.out.println(tu.allToString("!!"));
			//tu.replaceAllWords("2013-4-8 22:06:50 org.apache.catalina.core.AprLifecycleListener init,信息:\r\n3423234");
//			System.out.println(tu.findLineByKeyAnd("1","哈").size());
			//tu.findLineByKeyAnd(new String[]{"consumemoneyrecordold"},new File("f://res.txt"));
			
			//System.out.println(tu.findLineByKeyOr("1","哈").size());
			//tu.findLineByKeyOr(new File("d://res.txt"),"1","哈");
			//tu.append("你好啊啊22222 巍峨458qwqw");
			//tu.appendLineEnter();
			//tu.findLineByKeyOr(new String[]{"13213064570"} ,new File("d://13213064570.csv"));
			//tu.replaceAllWords("你好啊aaabb344");
			//System.out.println(tu.fileLinesNum());
			TextUtil keyTxt=new TextUtil("d://web.xml");
//			TextUtil valueTxt=new TextUtil("d://value.txt");
//			
//			List<String> keyList=keyTxt.fileLinesContent();
//			StringBuffer sb=new StringBuffer();
//			for(int i=0 ; i< keyList.size();i++){
//				keyList.set(i, keyList.get(i).trim());
//			}
//			System.out.println(StringUtil.underlineToJavaString(StringUtil.join(keyList,"myzzj,")));
			System.out.println(keyTxt.allToString(""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
