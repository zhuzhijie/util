package com.jing.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.jing.io.XMLUtil;

public class XMLUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			XMLUtil util=new XMLUtil(new File("d://web.xml"));
			System.out.println(util.readNodeList("welcome-file").item(1));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
