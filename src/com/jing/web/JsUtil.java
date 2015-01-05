package com.jing.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 用java操作 javascript 工具类。<br/>
 * 可以加载网络上的js，或者文件路径下的js，可以调用js文件中的函数。
 * @author 朱志杰 QQ：695520848
 * May 31, 2013 9:03:17 AM
 */
public class JsUtil {
	
	//js 文件操作对象。
	private Invocable inv=null;
	
	
	/**
	 * 功能：构造函数。(文件路径)
	 * @author 朱志杰 QQ：695520848
	 * May 31, 2013 9:05:22 AM
	 * @param jsFilePaths 文件路径下的js文件全路径，可以同时传入很多js路径。
	 * @throws ScriptException 读取js文件异常。
	 * @throws FileNotFoundException  js文件没有找到。
	 */
	public JsUtil(String... jsFilePaths) throws FileNotFoundException, ScriptException{
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByMimeType("text/javascript");
		for(String filePath : jsFilePaths){
			engine.eval(new FileReader(filePath));
		}
		inv = (Invocable) engine; 
	}
	
	/**
	 * 功能：构造函数。(网络地址)
	 * @author 朱志杰 QQ：695520848
	 * May 31, 2013 9:05:22 AM
	 * @param jsUrl js文件在网络上的全路径,可以同时传入多个JS的URL。
	 * @throws ScriptException 读取js文件异常。
	 * @throws IOException  从网路上加载js文件异常。
	 */
	public JsUtil(URL... jsUrls) throws ScriptException, IOException{
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByMimeType("text/javascript");
		//从网络读取js文件流
		for(URL url: jsUrls){
			InputStreamReader isr=new InputStreamReader(url.openStream());
			BufferedReader br=new BufferedReader(isr);
			engine.eval(br);
		}
		inv = (Invocable) engine; 
	}
	
	/**
	 * 功能：调用js中的顶层程序和函数。
	 * @param functionName js顶层程序和函数名。
	 * @author 朱志杰 QQ：695520848
	 * May 31, 2013 9:38:07 AM
	 * @return 程序或函数所返回的值 
	 * @throws NoSuchMethodException 如果不存在具有给定名称或匹配参数类型的方法。 
	 * @throws ScriptException 如果在调用方法期间发生错误。 
	 */
	public Object invokeFunction(String functionName,Object... args) throws ScriptException, NoSuchMethodException{
		return inv.invokeFunction(functionName,args); 
	}
	
}
