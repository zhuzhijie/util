package com.jing.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件相关操作辅助类。
 * @author 朱志杰 QQ：695520848
 * Jun 9, 2013 11:32:33 AM
 */
public class FileUtil {
	private static final String FOLDER_SEPARATOR = "/";
	private static final char EXTENSION_SEPARATOR = '.';
	
	/**
	 * 功能：复制文件或者文件夹。
	 * @author 朱志杰 QQ：862990787
	 * 2013-12-12 下午11:40:17
	 * @param inputFile 源文件
	 * @param outputFile 目的文件
	 * @param isOverWrite 是否覆盖(只针对文件)
	 * @throws IOException
	 */
	public static void copy(File inputFile,File outputFile,boolean isOverWrite) throws IOException{
		if(!inputFile.exists()){
			throw new RuntimeException(inputFile.getPath()+"源目录不存在!");
		}
		copyPri(inputFile,outputFile,isOverWrite);
    }
	
	/**
	 * 功能：为copy 做递归使用。
	 * @author 朱志杰 QQ：862990787
	 * 2013-12-12 下午11:40:03
	 * @param inputFile
	 * @param outputFile
	 * @param isOverWrite
	 * @throws IOException
	 */
	private static void copyPri(File inputFile,File outputFile,boolean isOverWrite) throws IOException{
		//是个文件。
		if(inputFile.isFile()){
			copySimpleFile(inputFile,outputFile,isOverWrite);
		}else{
			//文件夹
			if(!outputFile.exists()){
				outputFile.mkdir();
			}
			//循环子文件夹
			for(File child : inputFile.listFiles()){
				copy(child,new File(outputFile.getPath()+"/"+child.getName()),isOverWrite);
			}
		}
    }
	
	/**
	 * 功能：copy单个文件
	 * @author 朱志杰 QQ：862990787
	 * 2013-12-12 下午11:19:50
	 * @param inputFile 源文件
	 * @param outputFile 目标文件
	 * @param isOverWrite 是否允许覆盖
	 * @throws IOException
	 */
	private static void copySimpleFile(File inputFile,File outputFile,boolean isOverWrite) throws IOException{
		//目标文件已经存在
		if(outputFile.exists()){
			if(isOverWrite){
				if(!outputFile.delete()){
					throw new RuntimeException(outputFile.getPath()+"无法覆盖！");
				}
			}else{
				//不允许覆盖
				return ;
			}
		}
		
		InputStream in = null;  
        OutputStream out=null;
		try{
	        in = new FileInputStream(inputFile);  
	        out = new FileOutputStream(outputFile);  
	        byte[] buffer = new byte[1024];  
	        int read = 0;  
	        while((read = in.read(buffer))!= -1){  
	            out.write(buffer,0,read);  
	        } 
		}catch(IOException e){
			throw e;
		}finally{
			if(in!=null){
				in.close();  
			}
			if(out!=null){
				out.close();
			}
		}
    }  
	
	
	/**
	 * 功能：删除文件
	 * @author 朱志杰 QQ：862990787
	 * 2013-11-19 下午03:10:46
	 * @param file 文件
	 */
	public static void delete(File file) {
		deleteFile(file);
	}
	
	/**
	 * 功能：删除文件，内部递归使用
	 * @author 朱志杰 QQ：862990787
	 * 2013-11-19 上午10:01:56
	 * @param file 文件
	 * @return boolean true 删除成功，false 删除失败。
	 */
	private static void deleteFile(File file){
		if(file == null || !file.exists()){
			return;
		}
		//单文件
		if(!file.isDirectory()){
			boolean delFlag= file.delete();
			if(!delFlag){
				throw new RuntimeException(file.getPath()+"删除失败！");
			}else{
				return;
			}
		}
		//删除子目录
		for(File child : file.listFiles()){
			deleteFile(child);
		}
		//删除自己
		file.delete();
	}
	
	/**
	 * 从文件路径中抽取文件的扩展名,
	 * 例如. "mypath/myfile.txt" -> "txt".
	 * @param 文件路径
	 * @return 如果path为null，直接返回null。
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return path.substring(extIndex + 1);
	}
	
	/**
	 * 从文件路径中抽取文件名,
	 * 例如： "mypath/myfile.txt" -> "myfile.txt"。
	 * @param path 文件路径。
	 * @return 抽取出来的文件名, 如果path为null，直接返回null。
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}
	
	/**
	 * 功能：保存文件。
	 * @author 朱志杰 QQ：862990787
	 * 2013-12-12 下午10:01:30
	 * @param content 字节
	 * @param file 保存到的文件
	 * @throws IOException
	 */
	public static void save(byte[] content, File file)throws IOException{
		if (file==null) {
			throw new RuntimeException("保存文件不能为空");
		}
		if (content == null) {
			throw new RuntimeException("文件流不能为空");
		}
		InputStream is =null;
		try{
			is = new ByteArrayInputStream(content);
			save(is,file);
		}catch(IOException e){
			throw e;
		}finally{
			if(is!=null){
				is.close();
			}
		}
	}
	
	/**
	 * 功能：保存文件
	 * @author 朱志杰 QQ：862990787
	 * 2013-12-12 下午10:01:53
	 * @param streamIn 文件流
	 * @param file 保存到的文件
	 * @throws IOException
	 */
	public static void save(InputStream streamIn, File file)throws IOException {
		if (file==null) {
			throw new RuntimeException("保存文件不能为空");
		}
		if (streamIn == null) {
			throw new RuntimeException("文件流不能为空");
		}
		//输出流
		OutputStream streamOut =null;
		//文件夹不存在就创建。
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		streamOut = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			streamOut.write(buffer, 0, bytesRead);
		}
		streamOut.close();
		streamIn.close();
	}
}
