package com.jing.test;

import com.jing.io.TextUtil;

public class TestDes {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：695520848
	 * May 31, 2013 9:15:15 PM
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			TextUtil no=new TextUtil("d://a.txt");
			for(String line : no.fileLinesContent()){
				String [] splits=line.split("new String");
				//System.out.println(splits[0]+"  "+splits[1]);
				String[] words=splits[1].split("");
				String a=""; //用于拼接
				for(String word : words){
					if("[".equals(word) || "]".equals(word) || "".equals(word)
							|| "{".equals(word) || "\"".equals(word) || "}".equals(word)
							|| ")".equals(word) || ";".equals(word)){
						a+=word;
					}else{
						a+=(word+"\",\"");
					}
				}
				System.out.println(splits[0]+a);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
