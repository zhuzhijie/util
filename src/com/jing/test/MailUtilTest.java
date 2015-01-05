package com.jing.test;

import com.jing.util.MailUtil;

public class MailUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MailUtil m=new MailUtil("smtp.163.com","myzhijie@163.com","520jing!");
		m.setDisplayName("沙琪玛啊");
		m.addBCC("862990787@qq.com");
		try {
			m.addAttachfile("d://charge.log");
			m.addAttachfile("d://车云拍技术实现概要设计V0.1.docx");
			m.sendMail("你好", "08151720你好呵呵。", "695520848@qq.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
