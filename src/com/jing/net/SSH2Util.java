package com.jing.net;

import java.io.IOException;
import java.util.Vector;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import ch.ethz.ssh2.SFTPv3FileAttributes;

/**
 * 使用SSH2 远程连接Linux。<br/>
 * 连接SSH2完毕后进行连接关闭(调用close())，从来释放连接。<br/>
 * 依赖于 ganymed-ssh2-build210.jar
 * @author 朱志杰 QQ：862990787
 * Sep 2, 2013 9:36:04 AM
 */
public class SSH2Util {
	//连接
	private Connection conn=null;
	//sftp
	private SFTPv3Client sftp=null;
	
//	public static void main(String [] a){
//		SSH2Util s=new SSH2Util();
//		try {
//			System.out.println(s.login("116.255.149.143","runner",""));
//			//s.downloadFile("/home/runner/checkorder/telecom/MXT371_20130901", "g://");
//			System.out.println(s.ls("/home/runner").size());
//			//s.createFile("/home/runner/acc.txt");
//			//s.uploadFile("/home/runner/",new File( "d://first.log"));
//			//s.rm("/home/runner/c.a");
//			//s.rmdir("/home/runner/aaa");
////			s.createFile("/home/runner/my.doc");
////			s.mv("/home/runner/my.doc", "cccccc");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 功能：登录
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 9:41:45 AM
	 * @param hostname ssh2的hostname
	 * @param userName 用户名
	 * @param pwd 密码
	 * @throws IOException 
	 */
	public boolean login(String hostname,String userName,String pwd) throws IOException{
		return login(hostname,22,userName,pwd);
		
	}
	
	/**
	 * 功能：登录
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 9:41:45 AM
	 * @param hostname ssh2的hostname
	 * @param port 端口 SSH2的默认端口是22
	 * @param userName 用户名
	 * @param pwd 密码
	 * @throws IOException 
	 */
	public boolean login(String hostname,int port,String userName,String pwd) throws IOException{
		/* Create a connection instance */
		conn = new Connection(hostname,port);
		/* Now connect */
		conn.connect();
		/* Authenticate.
		 * If you get an IOException saying something like
		 * "Authentication method password not supported by the server at this stage."
		 * then please check the FAQ.
		 */
		boolean isAuthenticated = conn.authenticateWithPassword(userName, pwd);
		//sftp
		sftp=new SFTPv3Client(conn);
		return isAuthenticated;
	}
	
	/**
	 * 功能：移动文件。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 11:35:27 AM
	 * @param oldPath 旧文件路径
	 * @param newPath 新文件路径
	 * @throws java.io.IOException
	 */
	public void mv(String oldPath,String newPath)throws java.io.IOException{
		sftp.mv(oldPath, newPath);
	}
	
	/**
	 * 功能：创建文件用来写入。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 11:31:06 AM
	 * @param filePath 文件路径 
	 * @throws java.io.IOException
	 */
	public void createFile(String filePath) throws java.io.IOException{
		sftp.createFile(filePath);
	}
	
	/**
	 * 功能：得到文件的基本信息,不支持传入超链接。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 11:25:16 AM
	 * @param path 文件路径。
	 * @return SFTPv3FileAttributes
	 * @throws java.io.IOException
	 */
	public SFTPv3FileAttributes fileStat(String path) throws java.io.IOException{
		return sftp.stat(path);
	}
	
	/**
	 * 功能：删除文件。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 11:06:08 AM
	 * @param filePath 文件路径
	 * @throws java.io.IOException
	 */
	public void rm(String filePath)throws java.io.IOException{
		sftp.rm(filePath);
	}
	
	/**
	 * 功能：使用ssh2 上传文件。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 10:03:40 AM
	 * @param remoteTargetDirectory 上传到服务器上的文件夹路径。
	 * @param localFile 将要上传本地文件的路径。
	 * @throws IOException 
	 */
	public void uploadFile(String remoteTargetDirectory,String localFile) throws IOException{
		SCPClient scpClient = conn.createSCPClient();   
		scpClient.put(localFile, remoteTargetDirectory); //从本地复制文件到远程目录   
	}
	
	/**
	 * 功能：使用ssh2 下载文件。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 10:03:40 AM
	 * @param remoteFile 服务器上的文件路径。
	 * @param localTargetDirectory 下载到本地的文件夹路径。
	 * @throws IOException 
	 */
	public void downloadFile(String remoteFile,String localTargetDirectory) throws IOException{
		SCPClient scpClient = conn.createSCPClient();
		scpClient.get(remoteFile,localTargetDirectory);  //从远程获取文件  
	}
	
	/**
	 * 功能：ls某个文件夹并得到里面的文件信息。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 10:25:00 AM
	 * @param dirName 问价夹目录。
	 * @return Vector<SFTPv3DirectoryEntry>
	 * @throws IOException
	 */
	public Vector<SFTPv3DirectoryEntry> ls(String dirName) throws IOException{
		return sftp.ls(dirName);
	}
	
	/**
	 * 功能：连接SSH2完毕后务必进行连接关闭，从来释放连接。
	 * @author 朱志杰 QQ：862990787
	 * Sep 2, 2013 9:58:30 AM
	 */
	public void close(){
		/* Close the connection */
		conn.close();
		//close sftp client
		sftp.close();
	}
}
