package com.jing.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;




/**
 * 用来发送邮件的辅助类。<br/>
 * 主要依赖jar包：activation.jar，mail.jar。
 * @version 1.1
 * @author 朱志杰 QQ:862990787
 */
public class MailUtil {
	private String hostSmtp ; // 邮箱smtp
	private String hostAddress ; // 发件箱地址
	private String hostPwd ; // 发件箱密码
	//用于保存发送附件的文件名的集合
	private Vector<String> fileVector = new Vector<String>(); 
	//发件人的显示姓名,默认为发件人地址
	private String displayName=null;
	//抄送的人
	private List<String> ccMail=new ArrayList<String>();
	//密送的人
	private List<String> bccMail=new ArrayList<String>();
	
	/**
	 * 构造函数
	 * @param hostSmtp 发送邮件的邮箱smtp地址，例如：smtp.126.com
	 * @param hostAddress 发送邮件的邮箱地址
	 * @param hostPwd 发送邮件的邮箱密码
	 */
	public MailUtil(String hostSmtp,String hostAddress,String hostPwd){
		this.hostAddress=hostAddress;
		this.hostSmtp=hostSmtp;
		this.hostPwd=hostPwd;
		
		this.displayName=hostAddress;
	}
	
	/**
	 * 添加一个抄送人
	 * @param address 抄送人地址
	 */
	public void addCC(String address){
		ccMail.add(address);
	}
	
	/**
	 * 添加一个密送人
	 * @param address 密送人地址
	 */
	public void addBCC(String address){
		bccMail.add(address);
	}
	
	/**
	 * 设置发件人显示的名称
	 * @param displayName 显示的名称
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 向指定邮箱发送邮件
	 * @param title email标题
	 * @param content Email内容
	 * @param toAddress 接收邮箱地址 如：***@qq.com
	 * @throws Exception
	 */
	public void sendMail(String title, String content, String toAddress) throws Exception {
		String mail = content;
		// properties里面包含发送邮件服务器的地址
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", hostSmtp);
		mailProps.put("mail.smtp.auth", "true");
		SMTPAuthenticator smtpAuthenticator = new SMTPAuthenticator(
				hostAddress, hostPwd);
		Session mailSession = Session.getDefaultInstance(mailProps,
				smtpAuthenticator);
		MimeMessage message = new MimeMessage(mailSession);
		
		//设置发件人地址和显示名称
		Address from_address = new InternetAddress(hostAddress, displayName);  
		message.setFrom(from_address);
		//收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				toAddress, false));
		//抄送人
		if(!CollectionUtil.isEmpty(ccMail)){
			Address[] ccAddress=new Address[ccMail.size()];
			for(int i=0 ; i<ccMail.size() ; i++){
				ccAddress[i]=new InternetAddress(ccMail.get(i), false);
			}
			message.setRecipients(Message.RecipientType.CC, ccAddress);
		}
		//密送人
		if(!CollectionUtil.isEmpty(bccMail)){
			Address[] bccAddress=new Address[bccMail.size()];
			for(int i=0 ; i<bccMail.size() ; i++){
				bccAddress[i]=new InternetAddress(bccMail.get(i), false);
			}
			message.setRecipients(Message.RecipientType.BCC, bccAddress);
		}
		
		//标题
		message.setSubject(title);
		message.setText(mail);
		
		//整个邮件
		Multipart mp = new MimeMultipart();
		//邮件内容
        MimeBodyPart mbp = new MimeBodyPart();  
        mbp.setContent(content.toString(), "text/html;charset=gb2312");  
        mp.addBodyPart(mbp); 
        //邮件对应的附件
        if(!fileVector.isEmpty()){//有附件  
            Enumeration efile=fileVector.elements();  
            while(efile.hasMoreElements()){   
                mbp=new MimeBodyPart();  
                String filename=efile.nextElement().toString(); //选择出每一个附件名  
                FileDataSource fds=new FileDataSource(filename); //得到数据源  
                mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
                mbp.setFileName(MimeUtility.encodeText(fds.getName()));  //得到文件名同样至入BodyPart  
                mp.addBodyPart(mbp);  
            }    
            fileVector.removeAllElements();      
        }
        message.setContent(mp); //Multipart加入到信件  
        message.setSentDate(new Date());     //设置信件头的发送日期  
        //发送信件  
        message.saveChanges();   
		
		Transport.send(message);
	}
	
	/**
	 * 向邮件追加一个附件。  
	 * @param fname 附件的全路径
	 */
    public void addAttachfile(String fname){  
    	fileVector.addElement(fname);  
    }
}

/**
 * 用于Jmail返回邮箱账号和密码的校验(在这里被Jmail类所用)
 * 
 * @author 朱志杰
 * 
 */
class SMTPAuthenticator extends Authenticator {
	private String name = "";
	private String password = "";

	public SMTPAuthenticator(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(name, password);
	}

}
