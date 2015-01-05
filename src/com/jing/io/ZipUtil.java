package com.jing.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;


import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

import com.jing.util.CollectionUtil;

/**
 * Zip压缩综合类，依赖于commons-compress-1.5.jar。
 * @author 朱志杰 QQ：695520848
 * Aug 14, 2013 4:45:29 PM
 */
public class ZipUtil {
	
//	public static void main(String[] args){
//		try {
//			//new ZipUtil().decompressZip(new File("d://img.zip"),"img/pic201300006.jpg","d://");
//			new ZipUtil().decompressZip(new File("d://img.zip"),"flight.log","d://");
//			//new File("d://flight.log").delete();
//			//ZipUtil.compress(new File("D://测试压缩文件"),new File("d://img.zip"));
////			ZipUtil.compress(new File[]{new File("F:/movies/MTV/(KTV)[劉德華]世界第一等.mpg"),new File("d://ftp"),new File("e://ftp")},new File("d://压缩文件.zip"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	 /**
     * 把N多文件或文件夹压缩成zip。
     * @param files  需要压缩的文件或文件夹。
     * @param zipFilePath 压缩后的zip文件
	 * @throws IOException 压缩时IO异常。
     */
    public static void compress(File[] files,File zipFile) throws IOException {
        if(CollectionUtil.isEmpty(files)) {
        	return ;
        }
        
        ZipArchiveOutputStream out=null;
        try{
	        out = new ZipArchiveOutputStream(zipFile);
	        out.setUseZip64(Zip64Mode.AsNeeded);
	        //将每个文件用ZipArchiveEntry封装
	        for(File file : files) {
	            if(file == null) {
	            	continue;
	            }
	            compressOneFile(file,out,"");
	        }
        }catch(IOException e){
        	throw e;
        }finally{
        	 if(out != null) {
             	out.close();
             }
        }
    }
    
	
	/**
	 * 功能：压缩文件或文件夹。
	 * @author 朱志杰 QQ：695520848
	 * Aug 20, 2013 4:52:03 PM
	 * @param srcFile 源文件。
	 * @param destFile 压缩后的文件
	 * @throws IOException 压缩时出现了异常。
	 */
	public static void compress(File srcFile, File destFile) throws IOException {
		ZipArchiveOutputStream out = null;
		try {
			out = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(destFile), 1024));
			compressOneFile(srcFile,out,"");
		} finally {
			out.close();
		}
	}
	
    /**
	 * 功能：压缩单个文件,非文件夹。私有，不对外开放。
	 * @author 朱志杰 QQ：695520848
	 * Aug 20, 2013 4:52:03 PM
	 * @param srcFile 源文件，不能是文件夹。
	 * @param out 压缩文件的输出流。
	 * @param destFile 压缩后的文件
	 * @param dir 在压缩包中的位置,根目录传入/。
	 * @throws IOException 压缩时出现了异常。
	 */
	private static void compressOneFile(File srcFile, ZipArchiveOutputStream out,String dir) throws IOException {
		if(srcFile.isDirectory()){//对文件夹进行处理。
			ZipArchiveEntry entry=new ZipArchiveEntry(dir+srcFile.getName()+"/");
			out.putArchiveEntry(entry);
			out.closeArchiveEntry(); 
			//循环文件夹中的所有文件进行压缩处理。
			String[] subFiles=srcFile.list();
			for(String subFile : subFiles){
				compressOneFile(new File(srcFile.getPath()+"/"+subFile),out,(dir+srcFile.getName()+"/"));
			}
		}else{	//普通文件。
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(srcFile));
				//创建一个压缩包。
				ZipArchiveEntry entry = new ZipArchiveEntry(srcFile,dir+srcFile.getName());
				out.putArchiveEntry(entry);
				IOUtils.copy(is, out);
				out.closeArchiveEntry(); 
			} finally {
				if(is != null)
	                is.close();
			}
		}
	}
	
	/**
	 * 功能：解压缩zip压缩包下的所有文件。
	 * @author 朱志杰 QQ：695520848
	 * Aug 14, 2013 5:09:49 PM
	 * @param zipFile zip压缩文件
	 * @param dir 解压缩到这个路径下
	 * @throws IOException 文件流异常
	 */
	public void decompressZip(File zipFile,String dir) throws IOException{
        ZipFile zf = new ZipFile(zipFile);
        try {
            for (Enumeration<ZipArchiveEntry> entries = zf.getEntries();
                 entries.hasMoreElements(); ) {
                ZipArchiveEntry ze = entries.nextElement();
                //不存在则创建目标文件夹。
        		File targetFile = new File(dir, ze.getName());
        		//遇到根目录时跳过。
        		if(ze.getName().lastIndexOf("/")==(ze.getName().length()-1)){
        			continue;
        		}
        		//如果文件夹不存在，创建文件夹。
                if (!targetFile.getParentFile().exists()) {
                	targetFile.getParentFile().mkdirs();
                }
                
                InputStream i = zf.getInputStream(ze);
                OutputStream o=null;
                try {
					o=new FileOutputStream(targetFile);
					IOUtils.copy(i, o);
                } finally {
                	if(i!=null){
						i.close();
					}
					if (o!= null) {
		                o.close();
		            }
                }
            }
        } finally {
            zf.close();
        }
	}
	
	/**
	 * 功能：解压缩zip压缩包下的某个文件信息。
	 * @author 朱志杰 QQ：695520848
	 * Aug 14, 2013 5:09:49 PM
	 * @param zipFile zip压缩文件
	 * @param fileName 某个文件名,例如abc.zip下面的a.jpg，需要传入/abc/a.jpg。
	 * @param dir 解压缩到这个路径下
	 * @throws IOException 文件流异常
	 */
	public void decompressZip(File zipFile,String fileName,String dir) throws IOException{
		//不存在则创建目标文件夹。
		File targetFile = new File(dir, fileName);
        if (!targetFile.getParentFile().exists()) {
        	targetFile.getParentFile().mkdirs();
        }
        
		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips=zf.getEntries();
		ZipArchiveEntry zip=null;
		while(zips.hasMoreElements()){
			zip = zips.nextElement();
			if(fileName.equals(zip.getName())){
				OutputStream o=null;
				InputStream i = zf.getInputStream(zip);
				try {
					o=new FileOutputStream(targetFile);
					IOUtils.copy(i, o);
				}finally{
					if(i!=null){
						i.close();
					}
					if (o!= null) {
		                o.close();
		            }
				}
			}
		}
	}
	
	/**
	 * 功能：得到zip压缩包下的某个文件信息,只能在根目录下查找。
	 * @author 朱志杰 QQ：695520848
	 * Aug 14, 2013 5:09:49 PM
	 * @param zipFile zip压缩文件
	 * @param fileName 某个文件名,例如abc.zip下面的a.jpg，需要传入/abc/a.jpg。
	 * @return ZipArchiveEntry 压缩文件中的这个文件,没有找到返回null。
	 * @throws IOException 文件流异常
	 */
	public ZipArchiveEntry readZip(File zipFile,String fileName) throws IOException{
		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips=zf.getEntries();
		ZipArchiveEntry zip=null;
		while(zips.hasMoreElements()){
			zip = zips.nextElement();
			if(fileName.equals(zip.getName())){
				return zip;
			}
		}
		return null;
	}
	
	/**
	 * 功能：得到zip压缩包下的所有文件信息。
	 * @author 朱志杰 QQ：695520848
	 * Aug 14, 2013 5:09:49 PM
	 * @param zipFile zip压缩文件
	 * @return Enumeration<ZipArchiveEntry> 压缩文件中的文件枚举。
	 * @throws IOException 文件流异常
	 */
	public Enumeration<ZipArchiveEntry> readZip(File zipFile) throws IOException{
		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips=zf.getEntries();
		return zips;
	}
}
