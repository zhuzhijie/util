package com.jing.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于生成分页代码。<br/>
 * 有两种模式，第一种上一页、下一页、select选择页。第二种模式，显示当前页前后几页。<br/>
 * 在传参数时不要使用currentPage和1，这两个分页时进行将进行使用。
 * @author 朱志杰 QQ：695520848
 * Aug 1, 2013 1:43:42 PM
 */
public class PageUtil {
	//分页table的css 类名
	private static String tableClassName="pageTable";
	
	/**
	 * 功能：得到当前页分页信息。Map key totalPage 共计多少页，firstRecord 本页第一条记录序号，lastRecord 本页最后一条记录序号。<br/>
	 * 如果当前页数大于了最后一个页码，则返回有效记录之内的序号。
	 * @author 朱志杰 QQ：695520848
	 * Aug 2, 2013 5:49:57 PM
	 * @param currentPage 当前要显示第几页
	 * @param recordCountOfOnePage 每页显示多少条。
	 * @param recordCount 共计多少条记录。
	 * @return Map<String,Integer> 见功能介绍,三个参数任意一个为小于1的整数则无意义，直接返回null。
	 */
	public static Map<String,Integer> getCurrentPageInfo(int currentPage,int recordCountOfOnePage,int recordCount){
		Map<String,Integer> map=new HashMap<String,Integer>();
		if(currentPage<1 || recordCountOfOnePage<1 || recordCount<1){
			map.put("totalPage", 0);
			map.put("firstRecord", 0);
			map.put("lastRecord", 0);
			return map;
		}
		//总页数;
		int totalPage = recordCount / recordCountOfOnePage;
		if (recordCount % recordCountOfOnePage != 0) {
			totalPage = (recordCount / recordCountOfOnePage)+1;
		}
		//计算出来当页第一条和最后一条记录序号,做了页数超出的处理。
		int firstRecord=((currentPage-1)*recordCountOfOnePage)+1;
		if(firstRecord>=recordCount){
			firstRecord=recordCount;
		}
		int lastRecord=currentPage*recordCountOfOnePage;
		if(lastRecord>=recordCount){
			lastRecord=recordCount;
		}
		
		map.put("totalPage", totalPage);
		map.put("firstRecord", firstRecord);
		map.put("lastRecord", lastRecord);
		return map;
	}
	
	/**
	 * 功能：得到Ajax分页代码。分页代码table的样式class="+tableClassName+"，可以对其进行样式设计,采用第一种分页模式。<br/>
	 * 原理：点击任何一页超链接会调用functionName JS函数，并且会将currentPage传入到函数中，其他ajax处理直接在functionName中操作即可。<br/>
	 * @author 朱志杰 QQ：695520848
	 * Aug 1, 2013 1:52:13 PM
	 * @param functionName 点击上一页或者其他起码时触发的JS函数名，会自动将currentPage传入函数。<br/>
	 * @param currentPage 当前为第几页
	 * @param pageCount 一共有多少页
	 * @param recordCount 共有多少条记录，只是用来展示不参与运算。
	 * @return String
	 */
	public static String getAjaxPageHtml(String functionName,int currentPage,int pageCount,int recordCount){
		StringBuffer output = new StringBuffer();
		output.append("<table class="+tableClassName+" align=center width=100% cellspacing=0 cellpadding=0 >\n");
		output.append("<tr>\n");
		output.append("<td align=center >共" + recordCount+ "个</td>\n");
		if (currentPage <= 1) {
			output.append("<td align=center ><div align=left>首页</div></td>\n");
			output.append("<td  align=center ><div align=left>上一页</div></td>\n");
		} else {
			output.append("<td align=center > <div align=left><a href='javascript:void(0);' onclick=\""+functionName+"(1);\">首页</a></div></td>\n");
			output.append("<td align=center> <div align=left><a href='javascript:void(0);' onclick=\""+functionName+"("+(currentPage - 1)+ ");\">上一页</a></div></td>\n");
		}
		if (currentPage != pageCount && pageCount != 0) {
			output.append("<td align=center > <div align=left><a href='javascript:void(0);' onclick=\""+functionName+"("+(currentPage + 1)+ ");\">下一页</a></div></td>\n");
			output.append("<td align=center> <div align=left><a href='javascript:void(0);' onclick=\""+functionName+"("+pageCount+ ");\">尾页</a></div></td>\n");
		} else {
			output.append("<td align=center> <div align=left>下一页</div></td>\n");
			output.append("<td align=center> <div align=left>尾页</div></td>\n");
		}
		output.append("<td align=center> <div align=left>当前第"+ currentPage + "页/共" + pageCount + "页</div></td>\n");
		output.append("<td align=center  class=fContent><div align=left>到第\n");
		if (pageCount == 0) {
			output.append("<select name=currentPage disabled>\n");
		} else {
			output.append("<select name=currentPage id=\"currentPage\" onChange='"+functionName+"(this.value);'>\n");
		}
		for (int i = 1; i <= pageCount; i++) {
			if (i == currentPage)
				output.append("<option value=" + i + " selected>" + i+ "</option>\n");
			else
				output.append("<option value=" + i + ">" + i + "</option>\n");
		}
		output.append("</select>页\n");
		output.append("</tr></table>\n");
		return output.toString();
	}
	
	/**
	 * 设置分页表格css 的class名称，默认为pageTable。
	 * @param className css名称
	 */
	public static void setTableClassName(String className){
		PageUtil.tableClassName=className;
	}
	
	/**
	 * 功能：得到分页代码。分页代码table的样式class="+tableClassName+"，可以对其进行样式设计。<br/>
	 * 如果遇到中文会自动使用java.net.URLEncoder.encode编码，字符编码为UTF-8。解码时只需要使用java.net.URLDecoder.decode(值,"utf-8")。;
	 * @author 朱志杰 QQ：695520848
	 * Aug 1, 2013 1:52:13 PM
	 * @param url 点击上一页或者其他起码时触发的url，不需要带currentPage参数。<br/>
	 * currentPage参数会自动拼接到点击触发的url上。
	 * @param currentPage 当前为第几页
	 * @param pageCount 一共有多少页
	 * @param recordCount 共有多少条记录，只是用来展示不参与运算。
	 * @param style 样式 1 则使用第一种模式，否则使用第二种模式。
	 * @return String
	 */
	public static String getPageHtml(String url,int currentPage,int pageCount,int recordCount,int style){
		return getPageHtml(url, currentPage, pageCount, recordCount,style,null);
	}
	
	/**
	 * 功能：得到分页代码。分页代码table的样式class="+tableClassName+"，可以对其进行样式设计。<br/>
	 * 如果遇到中文会自动使用java.net.URLEncoder.encode编码，字符编码为UTF-8。解码时只需要使用java.net.URLDecoder.decode(值,"utf-8")。;
	 * @author 朱志杰 QQ：695520848
	 * Aug 1, 2013 1:52:13 PM
	 * @param url 点击上一页或者其他起码时触发的url，不需要带currentPage参数。<br/>
	 * currentPage参数会自动拼接到点击触发的url上。
	 * @param currentPage 当前为第几页
	 * @param pageCount 一共有多少页
	 * @param recordCount 共有多少条记录，只是用来展示不参与运算。
	 * @param style 样式 1 则使用第一种模式，否则使用第二种模式。
	 * @param request 自动将request中parameter的所有值集合追加到url上。
	 * @return String
	 */
	public static String getPageHtml(String url,int currentPage,int pageCount,int recordCount,int style,HttpServletRequest request){
		//url无任何参数时的处理。
		if(url.indexOf("?")==-1){
			url+=("?1=1");
		}
		//判断request是否为空，如果不为空追加parameter到url。
		Map<String,String> map = new HashMap<String,String>();  
		if(request!=null){
		    Enumeration paramNames = request.getParameterNames();  
		    while (paramNames.hasMoreElements()) {  
		      String paramName = (String) paramNames.nextElement();  
		      //注意过滤掉之前传的分页参数
		      if("currentPage".equals(paramName) || "1".equals(paramName)){
		    	  continue;
		      }
		      String[] paramValues = request.getParameterValues(paramName);  
		      if (paramValues.length == 1) {  
		        String paramValue = paramValues[0];  
		        if (paramValue.length() != 0) {  
		        	try {
		        		//编码参数,防止中文乱码
						url+=("&" + paramName + "=" +URLEncoder.encode(paramValue, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}  
		        	map.put(paramName, paramValue);  
		        }  
		      }  
		    }
		}
		//根据类型不同生成不同的风格。
		if(style==1){
			return pageHtmlOne(url,currentPage,pageCount,recordCount,map);
		}else{
			return pageHtmlTwo(url,currentPage,pageCount,recordCount);
		}
	}

	/**
	 * 功能：第二种分页代码。
	 * @author 朱志杰 QQ：695520848
	 * Aug 1, 2013 1:52:13 PM
	 * @param url 点击上一页或者其他起码时触发的url，不需要带currentPage参数。<br/>
	 * currentPage参数会自动拼接到点击触发的url上。
	 * @param currentPage 当前为第几页
	 * @param pageCount 一共有多少页
	 * @param recordCount 共有多少条记录，只是用来展示不参与运算。
	 * @return String
	 */
	private static String pageHtmlTwo(String url, int currentPage, int pageCount,int recordCount) {
		StringBuffer output = new StringBuffer();
		output.append("<table class="+tableClassName+" align=center width=100% cellspacing=0 cellpadding=0 >\n");
		output.append("<tr><td>\n");
		output.append("共" + recordCount + "个&nbsp;&nbsp;");

		if (currentPage <= 1) {
			output.append("首页&nbsp;&nbsp;");
			output.append("上一页&nbsp;&nbsp;");
		} else {
			output.append("<a href='" + url+ "&currentPage=1'>首页&nbsp;&nbsp;</a>\n");
			output.append("<a href='" + url + "&currentPage=" + (currentPage - 1)+ "'>上一页&nbsp;&nbsp;</a>\n");
		}
		// =============================前面四个页码==============================
		if (pageCount > 9) {
			if (currentPage > 4) {
				if (currentPage > (pageCount - 4)) {// 为真时直接输出最后9个页码
					for (int c = pageCount - 8; c <= pageCount; c++) {
						if (c == currentPage) {// 当前页不显示链接
							output.append("[" + c + "]&nbsp;");
						} else {
							output.append("<a href=" + url + "&currentPage=" + c+ ">[" + c + "]</a>&nbsp;");
						}
					}
				} else {// 经过程序来生成每一页的页码
					int a = currentPage - 1;
					int b = currentPage - 4;
					for (int i = b; i <= a; i++) {
						if (i < 1) {
							break;
						}
						if (i == currentPage) {// 当前页不显示链接
							output.append("[" + i + "]&nbsp;");
						} else {
							output.append("<a href=" + url + "&currentPage=" + i+ ">[" + i + "]</a>&nbsp;");
						}
					}
					// =============================前面四个页码==============================
					// =============================中间一个页码==============================
					output.append("[" + currentPage + "]&nbsp;");
					// =============================中间一个页码==============================
					// =============================后面四个页码==============================
					for (int j = currentPage + 1; j <= (currentPage + 4); j++) {
						if (j > pageCount) {
							break;
						}
						if (j == currentPage) { // 当前页不显示链接
							output.append("[" + j + "]&nbsp;");
						} else {
							output.append("<a href=" + url + "&currentPage=" + j
									+ ">[" + j + "]</a>&nbsp;");
						}
					}
				}
			}
			// =============================后面四个页码==============================
			else {
				for (int k = 1; k <= 9; k++) {
					if (k == currentPage) { // 当前页不显示链接
						output.append("[" + k + "]&nbsp;");
					} else {
						output.append("<a href=" + url + "&currentPage=" + k
								+ ">[" + k + "]</a>&nbsp;");
					}
				}
			}
		} else {
			for (int s = 1; s <= pageCount; s++) {// 当rs.pagecount<9的时候直接输出所有的页码
				if (s == currentPage) { // 当前页不显示链接
					output.append("[" + s + "]&nbsp;");
				} else {
					output.append("<a href=" + url + "&currentPage=" + s + ">["+ s + "]</a>&nbsp;");
				}
			}
		}

		// =============================输出下一页,末页==============================
		if (currentPage == pageCount) {
			output.append("下一页&nbsp;&nbsp;末页&nbsp;&nbsp;");
		} else {
			output.append("<a href=" + url + "&currentPage=" + (currentPage + 1)+ ">下一页&nbsp;&nbsp;</a>&nbsp;");
			output.append("<a href=" + url + "&currentPage=" + pageCount+ ">末页&nbsp;&nbsp;</a>&nbsp;");
		}
		// =============================输出下一页,末页==============================
		output.append("当前第" + currentPage + "页/共" + pageCount + "页");
		output.append("</tr></td></table>\n");
		return output.toString();
	}

	/**
	 * 功能：第一种分页代码。
	 * @author 朱志杰 QQ：695520848
	 * Aug 1, 2013 1:52:13 PM
	 * @param url 点击上一页或者其他起码时触发的url，不需要带currentPage参数。<br/>
	 * currentPage参数会自动拼接到点击触发的url上。
	 * @param currentPage 当前为第几页
	 * @param pageCount 一共有多少页
	 * @param recordCount 共有多少条记录，只是用来展示不参与运算。
	 * @param map 从request中取出来的键值对应的参数，填充到select的form中。
	 * @return String
	 */
	private static String pageHtmlOne(String url, int currentPage, int pageCount,int recordCount, Map<String,String> map) {
		StringBuffer output = new StringBuffer();
		output.append("<form method=post name=page action='"+ url+ "'>\n"
						+ "<table class="+tableClassName+" align=center width=100% cellspacing=0 cellpadding=0 >\n");
		output.append("<tr>\n");
		output.append("<td align=center >共" + recordCount+ "个</td>\n");
		if (currentPage <= 1) {
			output.append("<td align=center ><div align=left>首页</div></td>\n");
			output.append("<td  align=center ><div align=left>上一页</div></td>\n");
		} else {
			output.append("<td align=center > <div align=left><a href='"+ url + "&currentPage=1'>首页</a></div></td>\n");
			output.append("<td align=center> <div align=left><a href='"+ url+ "&currentPage="+ (currentPage - 1)+ "'>上一页</a></div></td>\n");
		}
		if (currentPage != pageCount && pageCount != 0) {
			output.append("<td align=center> <div align=left><a href='"+ url+ "&currentPage="+ (currentPage + 1)+ "'>下一页</a></div></td>\n");
			output.append("<td align=center> <div align=left><a href='"+ url+ "&currentPage="+ pageCount+ "'>尾页</a></div></td>\n");
		} else {
			output.append("<td align=center> <div align=left>下一页</div></td>\n");
			output.append("<td align=center> <div align=left>尾页</div></td>\n");
		}
		output.append("<td align=center> <div align=left>当前第"+ currentPage + "页/共" + pageCount + "页</div></td>\n");
		output.append("<td align=center  class=fContent><div align=left>到第\n");
		if (pageCount == 0) {
			output.append("<select name=currentPage onChange='this.form.submit()' disabled>\n");
		} else {
			output.append("<select name=currentPage onChange='this.form.submit()'>\n");
		}
		for (int i = 1; i <= pageCount; i++) {
			if (i == currentPage)
				output.append("<option value=" + i + " selected>" + i+ "</option>\n");
			else
				output.append("<option value=" + i + ">" + i + "</option>\n");
		}
		output.append("</select>页\n");
		//将map中的信息放入到隐藏域
		for(String key : map.keySet()){
			output.append("<input type=\"hidden\" name=\""+key+"\" id=\""+key+"\" value=\""+map.get(key)+"\" />\n");
		}
		output.append("</tr></table></form>\n");
		return output.toString();
	}
}