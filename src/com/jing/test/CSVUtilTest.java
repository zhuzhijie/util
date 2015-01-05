package com.jing.test;

import java.io.IOException;

import com.jing.image.TwoDimensionUtil;


public class CSVUtilTest {

	/**
	 * 功能：
	 * @author 朱志杰 QQ：862990787
	 * 2013-10-10 上午11:07:25
	 * @param args
	 */
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		CsvWriterUtil c=new CsvWriterUtil("d://abc.txt");
//		try {
//			c.writeRecord(new String[]{"1","2,\"3"});
//			c.writeComment("sdfsf");
//			c.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(StringEscapeUtils.escapeCsv("23\\56"));
//		
//		try {
//			CsvReaderUtil cd=new CsvReaderUtil("d://VirusDb.csv");
//			while(cd.readRecord()){
//				String []lineVal=cd.getValues();
//				for(String line : lineVal){
//					System.out.print(line+"---");
//				}
//				System.out.println("");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		HttpCilentUtil hp=new HttpCilentUtil();
//		try {
//			hp.downFile("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHz8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL29VeE1nTm5sTnJoUEcwaW1RR0FsAAIEjfWjUwMEAAAAAA%3D%3D", "d://a.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		TwoDimensionUtil two=new TwoDimensionUtil();
		try {
			String b=two.decoderQRCode("d://a.jpg");
			System.out.println(b);
			two.encoderQRCode(b, "d://b.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
