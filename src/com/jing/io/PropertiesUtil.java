package com.jing.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取Properties综合类,默认绑定到classpath下的config.properties文件。
 * @author 朱志杰
 */
public class PropertiesUtil {
	//配置文件的路径
	private String configPath=null;
	
	/**
	 * 配置文件对象
	 */
	private Properties props=null;
	
	
	/**
	 * 默认构造函数，用于sh运行，自动找到classpath下的config.properties。
	 */
	public PropertiesUtil() throws IOException{
		String propertiesUtilPath=PropertiesUtil.class.getResource("PropertiesUtil.class").getPath();
		//截取路径到classes
		String classPath=propertiesUtilPath.substring(0,propertiesUtilPath.indexOf("classes/"))+"classes/";
		configPath=classPath.substring(1)+"config.properties";
		props = new Properties();
		InputStream in = null;
		try{
			in=new BufferedInputStream(new FileInputStream(configPath));
			props.load(in);
			propertiesUtilPath=null;
			classPath=null;
		}catch(IOException e){
			throw e;
		}finally{
			//关闭资源
			if(in!=null){
				in.close();
			}
		}
		
	}
	
	/**  
    * 更新（或插入）一对properties信息(主键及其键值)  
    * 如果该主键已经存在，更新该主键的值；  
    * 如果该主键不存在，则插件一对键值。  
    * @param keyname 键名  
    * @param keyvalue 键值  
    * @param note 备注信息,中文会乱码。
	 * @throws IOException 
    */   
    public void insertOrUpdateValue(String keyname,String keyvalue,String note) throws IOException { 
    	OutputStream fos =null;
        try {   
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。   
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。   
            fos = new FileOutputStream(configPath);   
            props.setProperty(keyname, keyvalue);   
            // 以适合使用 load 方法加载到 Properties 表中的格式，   
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
            props.store(fos,note); 
        } catch (IOException e) {   
            throw e;
        }finally{
        	if(fos!=null){
        		fos.close();
        	}
        }
    }
	
	/**
	 * 根据key值读取配置的值
	 * Jun 26, 2010 9:15:43 PM
	 * @author 朱志杰
	 * @param key key值
	 * @return key 键对应的值 
	 * @throws IOException 
	 */
	public String readValue(String key) throws IOException {
		return  props.getProperty(key);
	}
	
	/**
	 * 读取properties的全部信息
	 * Jun 26, 2010 9:21:01 PM
	 * @author 朱志杰
	 * @throws FileNotFoundException 配置文件没有找到
	 * @throws IOException 关闭资源文件，或者加载配置文件错误
	 * 
	 */
	public Map<String,String> readAllProperties() throws FileNotFoundException,IOException  {
		//保存所有的键值
		Map<String,String> map=new HashMap<String,String>();
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = props.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}

	/**
	 * 得到某一个类的路径
	 * @param name
	 * @return
	 */
	private String getPath(Class name) {
		String strResult = null;
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			strResult = name.getResource("/").toString().replace("file:/", "")
					.replace("%20", " ");
		} else {
			strResult = name.getResource("/").toString().replace("file:", "")
					.replace("%20", " ");
		}
		return strResult;
	}
	
//	/**
//	 * 设置某个key的值,并保存至文件。
//	 * Jun 26, 2010 9:15:43 PM
//	 * @author 朱志杰
//	 * @param key key值
//	 * @param key 键对应的值 
//	 * @param note 备注信息
//	 * @throws IOException 
//	 */
//	public void setValue(String key,String value,String note) throws IOException {
//		Properties prop = new Properties();
//		try {
//			InputStream fis = new FileInputStream(this.configPath);
//			// 从输入流中读取属性列表（键和元素对）
//			prop.load(fis);
//			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
//			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
//			OutputStream fos = new FileOutputStream(this.configPath);
//			prop.setProperty(key, value);
//			// 以适合使用 load 方法加载到 Properties 表中的格式，
//			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
//			prop.store(fos,note);
//			//关闭文件
//			fis.close();
//			fos.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
