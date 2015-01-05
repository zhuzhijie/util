package com.jing.web;

import java.io.IOException;

/**
 * 脚本助手，主要用来在后台输出js的confirm以及alert等。<br/>
 * 例如：response.getWrite().print(ScriptUtil.Reload());//刷新当前页面。（重新加载）
 * @author 朱志杰 QQ：695520848
 * Jul 31, 2013 9:35:25 AM
 */
public class ScriptUtil {
	
	/**
	 * 功能：将当前页面进行重新加载。（刷新）
	 * @author 朱志杰 QQ：695520848
	 * Jul 31, 2013 9:36:53 AM
	 * @return String
	 */
	public static String reload()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("<script language=\"javascript\">\n");
		sb.append("window.location.href=window.location.href;");
		sb.append("\n");
		sb.append("</script>");
        return sb.toString();
	}
	
	/**
	 * 功能：弹出alert对话框，并在点击确定后跳转到某个url。
	 * @author 朱志杰 QQ：695520848
	 * Jul 31, 2013 9:38:40 AM
	 * @param msgInfo alert的内容。
	 * @param url 点击alert的确定后跳转到的url。
	 * @return String
	 */
	public static String showAlert(String msgInfo,String url){  
		String strMessage="<script language='javascript'>";
		strMessage=strMessage + "alert('" + msgInfo + "')" + ";";
		if (!"".equals(url) && url!=null)
			strMessage=strMessage + "window.location.href='" + url + "';";
		strMessage=strMessage + "</script>";
		return strMessage;
	}
	
	/**
	 * 功能：弹出alert对话框，并在点击确定后返回上个页面。
	 * @author 朱志杰 QQ：695520848
	 * Jul 31, 2013 9:41:11 AM
	 * @param msgInfo alert的内容。
	 * @return String
	 */
	public static String showAlertAndBack(String msgInfo){
		String strMessage="<script language='javascript'>";
		strMessage=strMessage + "alert('" + msgInfo + "')" + ";";
		strMessage=strMessage+"history.go(-1);</script>";
		return strMessage;
	}
	
	/**
	 * 功能：弹出confirm，当用户选择是时跳转到指定url，否则返回。
	 * @author 朱志杰 QQ：695520848
	 * Jul 31, 2013 2:27:19 PM
	 * @param msgInfo confirm信息
	 * @param url 点击确定后跳转到的url。
	 * @return String
	 * @throws IOException
	 */
	public String showConfirm(String msgInfo,String url) throws IOException
	{  
		String strMessage="<script language='javascript'>";
		strMessage=strMessage + "if(confirm('" + msgInfo + "'))";
		if (!"".equals(url) && url!=null)
			strMessage=strMessage + "window.location.href='" + url + "';";
		strMessage=strMessage + "else history.go(-1);</script>";
		return strMessage;
	}
	
	/**
	 * 功能：弹出confirm，当用户选择是时跳转到指定url，点击否时跳转到另一个url。
	 * @author 朱志杰 QQ：695520848
	 * Jul 31, 2013 2:27:19 PM
	 * @param msgInfo confirm信息
	 * @param urlOK 点击确定后跳转到的url。
	 * @param urlCancle 点击取消时跳转到的url。
	 * @return String
	 * @throws IOException
	 */
	public String showConfirm(String msgInfo,String urlOK,String urlCancle) throws IOException
	{  
		String strMessage="<script language='javascript'>";
		strMessage=strMessage + "if(confirm('" + msgInfo + "'))";
		if (!"".equals(urlOK) && urlOK!=null){
			strMessage=strMessage + "window.location.href='" + urlOK + "';";
		}
		strMessage=strMessage + "else window.location.href='" + urlCancle + "';</script>";
		return strMessage;
	}	

}