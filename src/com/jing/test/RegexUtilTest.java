package com.jing.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jing.util.RegexUtil;


/**
 * 正则表达式测试类。
 * @author 朱志杰 QQ:862990787
 *
 */
public class RegexUtilTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//验证邮箱总规则。
		//邮箱名允许大小写字母数字下划线，域名可以含数字、大小写字母、点、下划线，如果有些邮箱要求不同，可自行修改。
		RegexUtil r=new RegexUtil();
		//@之前规则
		RegexUtil before=new RegexUtil(RegexUtil.letterAndNumAndUnderLine);//允许大小写字母和下划线
		before.repeatOneOrMore();//允许重复1-N次
		r.append(before);//将@之前的规则追加到总规则
		r.append("@");//追加上@符号
		
		//@之后到最后一个域名点之前的规则
		RegexUtil after=new RegexUtil(RegexUtil.letterAndNumAndUnderLine);//允许大小写字母和下划线
		after.or(".");//允许点，防止邮箱二级域名,如：@vip.qq.com
		after.or("-");//域名中允许横线
		after.repeatOneOrMore();//允许重复1-N次
		r.append(after);//追加到总规则
		//顶级域名前的点
		r.append(".");
		
		//顶级域名的规则
		RegexUtil last=new RegexUtil(RegexUtil.lowLetter);//顶级域名只允许小写字母
		last.repeatOneOrMore();////允许重复1-N次
		r.append(last);
		System.out.println(r);//打印总正则：[a-zA-Z0-9_]+@[a-zA-Z0-9_\.-]+\.[a-z]+
		
		//RegexUtil r=new RegexUtil("[1-9][0-9]*");
		
		//Pattern p = Pattern.compile(r.toString());
		//Matcher m = p.matcher("1");
		System.out.println(r.isMatches("023"));
		System.out.println(r.replaceMatches("sdfsdf564165werfds57sdfsdf151wer46554", "-"));
		//System.out.println(r.getMatches("sdfsdf564165werfds57sdfsdf151wer46554").size());
		for(String a : r.getMatches("sdfsdf564165werfds57sdfsdf151wer46554")){
			System.out.println(a);
		}
	}

}
