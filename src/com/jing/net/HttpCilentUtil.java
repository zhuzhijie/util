package com.jing.net;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.jing.util.CollectionUtil;

/**
 * HttpCilent 工具类。<br/>
 * 依赖于httpclient-4.2.1.jar,httpclient-cache-4.2.1.jar,httpcore-4.2.1.jar,httpmime-4.2.1.jar.
 * @author 朱志杰 QQ：695520848
 * Jul 2, 2013 5:52:25 PM
 */
public class HttpCilentUtil {
	private HttpClient httpclient = new DefaultHttpClient();
	//cookie
	private CookieStore cookieStore=null;
	/** 
     * http链接超时时间。
     */  
    private int timeOutOfHttp = 5000;  
    
    /**
     * http请求的连接超时时间设置
     * @param timeOutOfHttp 超时时间，单位为秒。
     */
    public void setTimeOutOfHttp(int timeOutOfHttp) {
    	this.timeOutOfHttp = timeOutOfHttp;
	}

	/**
	 * 功能：使用get请求一个url，得到返回的response信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:00:49 PM
	 * @param url 将要请求的url.
	 * @return HttpResponse
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public HttpResponse requestUrl4Response(String url) throws ClientProtocolException, IOException{
		return requestUrl4Response(url,"GET");
	}
	
	/**
	 * 功能：请求一个url,得到返回的response信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:04:59 PM
	 * @param url 需要请求的url。
	 * @param method 使用的方法，POST或者GET。
	 * @return HttpResponse
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public HttpResponse  requestUrl4Response(String url,String method) throws ClientProtocolException, IOException{
		return requestUrl4Response(url,method,null);
	}
	
	/**
	 * 功能：请求一个url,得到返回的response信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:04:59 PM
	 * @param url 需要请求的url。
	 * @param method 使用的方法，POST或者GET。
	 * @param argsMap ,当method为POST时，在这里传入post的参数信息。
	 * @return HttpResponse
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public HttpResponse requestUrl4Response(String url,String method,Map<String,String> argsMap) throws ClientProtocolException, IOException{
		return requestUrl4Response(url,method,argsMap,"text/html;charset=UTF-8");
	}
	
	/**
	 * 功能：请求一个url,得到返回的response信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:04:59 PM
	 * @param url 需要请求的url。
	 * @param method 使用的方法，POST或者GET。
	 * @param argsMap ,当method为POST时，在这里传入post的参数信息。
	 * @param ContentType 文本类型，例如"text/html;charset=UTF-8".注意POST时会自动修改为"application/x-www-form-urlencoded"。
	 * @return HttpResponse
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public HttpResponse requestUrl4Response(String url,String method,Map<String,String> argsMap,String ContentType) throws ClientProtocolException, IOException{
		((DefaultHttpClient)httpclient).setCookieStore(cookieStore);
        //页面的返回信息。
        HttpResponse response =null;
        
		//POST请求
		if("POST".equals(method.toUpperCase())){
			HttpPost httpPost = new HttpPost(url);
			//表单内容。
			if(!CollectionUtil.isEmpty(argsMap)){
		        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		        for(String key : argsMap.keySet()){
		        	nvps.add(new BasicNameValuePair(key, argsMap.get(key)));
		        }
		        httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			}
			response=httpclient.execute(httpPost);
		}else{
			//get请求
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Content-Type", ContentType);
			response=httpclient.execute(httpGet);
		}
        cookieStore=((DefaultHttpClient)httpclient).getCookieStore();
        return response;
	}
	
	/**
	 * 功能：使用get请求一个url，得到返回的信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:00:49 PM
	 * @param url 将要请求的url.
	 * @return String
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public String requestUrl(String url) throws ClientProtocolException, IOException{
		return requestUrl(url,"GET");
	}
	
	/**
	 * 功能：请求一个url,得到返回的信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:04:59 PM
	 * @param url 需要请求的url。
	 * @param method 使用的方法，POST或者GET。
	 * @return String
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public String requestUrl(String url,String method) throws ClientProtocolException, IOException{
		return requestUrl(url,method,null);
	}
	
	/**
	 * 功能：请求一个url,得到返回的信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:04:59 PM
	 * @param url 需要请求的url。
	 * @param method 使用的方法，POST或者GET。
	 * @param argsMap ,当method为POST时，在这里传入post的参数信息。
	 * @return String
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public String requestUrl(String url,String method,Map<String,String> argsMap) throws ClientProtocolException, IOException{
		return requestUrl(url,method,argsMap,"text/html;charset=UTF-8");
	}
	
	/**
	 * 功能：请求一个url,得到返回的信息。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:04:59 PM
	 * @param url 需要请求的url。
	 * @param method 使用的方法，POST或者GET。
	 * @param argsMap ,当method为POST时，在这里传入post的参数信息。
	 * @param ContentType 文本类型，例如"text/html;charset=UTF-8".注意POST时会自动修改为"application/x-www-form-urlencoded"。
	 * @return String
	 * @throws IOException 请求IO异常
	 * @throws ClientProtocolException 请求客户端协议异常
	 */
	public String requestUrl(String url,String method,Map<String,String> argsMap,String ContentType) throws ClientProtocolException, IOException{
		((DefaultHttpClient)httpclient).setCookieStore(cookieStore);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        //页面的返回信息。
        String confirmResponse ="";
        
		//POST请求
		if("POST".equals(method.toUpperCase())){
			HttpPost httpPost = new HttpPost(url);
			//表单内容。
			if(!CollectionUtil.isEmpty(argsMap)){
		        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		        for(String key : argsMap.keySet()){
		        	nvps.add(new BasicNameValuePair(key, argsMap.get(key)));
		        }
		        httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			}
			confirmResponse=httpclient.execute(httpPost,responseHandler);
		}else{
			//get请求
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Content-Type", ContentType);
			confirmResponse=httpclient.execute(httpGet,responseHandler);
		}
        cookieStore=((DefaultHttpClient)httpclient).getCookieStore();
        return confirmResponse;
	}
	
	/**
	 * 功能：下载文件到指定目录下。
	 * @author 朱志杰 QQ：695520848
	 * Jul 2, 2013 6:19:41 PM
	 * @param url 文件路径。
	 * @param filePath 文件保存的全路径。例如：d://a.jpg
	 * @throws IOException 下载文件时的一些IO异常。
	 */
	public void downFile(String url,String filePath) throws IOException{
		HttpClient httpclient=new DefaultHttpClient();
		httpclient.getParams().setBooleanParameter( "http.protocol.expect-continue" , false );//解决Too many open files
		
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeOutOfHttp);  
        HttpGet get = new HttpGet(url);  
        get.addHeader("Connection", "close");//解决Too many open files
        
        get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeOutOfHttp);  
        HttpResponse resonse = httpclient.execute(get);
        if (resonse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        	throw new RuntimeException("下载验证码图片失败getStatusCode:"+resonse.getStatusLine().getStatusCode());
        }
        //图片内容
        HttpEntity entity = resonse.getEntity();  
        if (entity == null) {
        	throw new RuntimeException("下载验证码图片失败！");
        }
        //保存到文件中。
        BufferedOutputStream out = null;  
        byte[] bit = EntityUtils.toByteArray(entity);  
        if (bit.length <= 0) {
        	throw new RuntimeException("下载验证码图片大小为0！");
        }
        try {  
            out = new BufferedOutputStream(new FileOutputStream(filePath));  
            out.write(bit);  
            out.flush();  
        } finally {  
            if (out != null)  
                out.close();  
        }
	}
	
	
	public HttpClient getHttpclient() {
		return httpclient;
	}
	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
	public CookieStore getCookieStore() {
		return cookieStore;
	}
	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}
	
	/**
	 * 设置httpclient为无证书操作。
	 * @param base
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	private HttpClient wrapClient(HttpClient base){
		try{
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {
	                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {}
	                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {}
	                public X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                }
	        };
	        ctx.init(null, new TrustManager[]{tm}, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = base.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        return new DefaultHttpClient(ccm, base.getParams());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	 }
	
}
