// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BarCodeServlet.java

package com.jing.image;

import com.sun.image.codec.jpeg.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 生成条形码的servlet。<br/>
 * 实现功能: 显示各种规格的条形码(18种),打印后可以被扫描设备扫描.<br/>
 *1.在web.xml中配置一个生成条形码的servlet,如下：<br/>
 *	...
 *	&lt;servlet><br/>
 *		&lt;servlet-name>CreateBarCode&lt;/servlet-name><br/>
 *		&lt;servlet-class>net.maxt.util.barcode.BarCodeServlet&lt;/servlet-class><br/>
 *	&lt;/servlet>
 *	...<br/>
 *	&lt;servlet-mapping><br/>
 *		&lt;servlet-name>CreateBarCode&lt;/servlet-name><br/>
 *		&lt;url-pattern>/CreateBarCode&lt;/url-pattern><br/>
 *	&lt;/servlet-mapping><br/>
 *	...<br/>
 *<br/>
 *这样就可以使用CreateBarCode这个servlet来生成条形码了。       <br/>
 *3.前台引用例子(BarcodeExample.jsp)：<br/>
 *<br/>
 *4.关于条形码使用时可以设置的一些参数：<br/>
 *  ------------------------------------<br/>
 *  barType(默认为CODE128):   条码类型.支持18钟类型,分别是 CODE39,CODE39EXT,INTERLEAVED25,CODE11,CODABAR,MSI,UPCA,IND25,MAT25,CODE93,EAN13,EAN8,UPCE,CODE128,CODE93EXT,POSTNET,PLANET,UCC128 .<br/>
 *  code:   要打印的条码内容.    <br/>
 *  width(默认为自适应,一般不用自行设置):   图片宽度.width,height要同时都设置才有效.     <br/>
 *  height(默认为自适应,一般不用自行设置):   图片高度.width,height要同时都设置才有效.     <br/>
 *  st(默认为y显示):   是否显示条码内容(show   text).默认会在条码图片下方显示条码内容,有效值为y和n.     <br/>
 *  textFont(默认为Arial|PLAIN|11):   条码文本的字体,字体有效格式为<font   name>|<style>|<size>.Style可以是PLAIN,ITALIC或BOLD.     <br/>
 *  fontColor(默认为黑色):   条码文本的颜色,有效值为RED,BLUE,GREEN,BLACK,GRAY,LIGHTGRAY,WHITE,DARKGRAY,YELLOW,ORANGE,CYAN和MAGENTA.     <br/>
 *  barColor(默认为黑色):   条码的颜色.     <br/>
 *  backColor(默认为白色):   图片背景颜色.     <br/>
 *  rotate(默认为0):   设置条码旋转角度.有效值为0(不旋转),90(旋转90度),180(旋转180度),270(旋转270度).   <br/>
 *  barHeightCM(默认为1厘米):   条码的高度.     <br/>
 *  x(默认为0.03厘米,一般不用自行调整):   条码符号中窄单元的标称尺寸,最小可设置为0.001即1象素,通常以0.03递增.   <br/>
 *  n(默认为2倍):   宽窄比,平均宽条的条宽与平均宽空的空宽之和(条码字符间隔不计在内)除以两倍窄单元尺寸.它是宽度调节编码法中的技术参数.   <br/>
 *  leftMarginCM(默认为0.3厘米):   条码与图片左右边的距离.     <br/>
 *  topMarginCM(默认为0.2厘米):   条码与图片上下边的距离.     <br/>
 *  checkCharacter(默认为y,一般不用自行设置):   是否自动计算check   character,有效值为y和n.     <br/>
 *  checkCharacterInText(默认为y,一般不用自行设置):   条码内容是否自动计算check   character,有效值为y和n.     <br/>
 *  Code128Set(默认为0自动选择,一般不用设置):   设置CODE128中使用的字符集.有效值为0,A,B,C.     <br/>
 *  UPCESytem(默认为0,一般不用自行设置):   UPCE中使用的编码系统.有效值为0和1.     <br/>
 *  ------------------------------------<br/>
 *  其中关键的参数主要是 barType,code 这2个.另外把checkCharacter和checkCharacterInText这2个参数设置成n,如果是y的话,会在你的条形码后面追加一个校验码,虽然不影响使用(扫描设备扫描条形码得到数据不会出现校验码),但是看着比较别扭.<br/>
 *	<br/>
 *5.还可以在applet中使用,具体使用参数请对照net.maxt.test.BarCodeApplet.java文件,与WEB使用方式差不多.
 *<br/> 
 * @author 朱志杰 QQ：695520848
 * Jul 16, 2013 9:34:55 AM
 */
public class BarCodeServlet extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -91500950829589648L;
	
	public BarCode barcode;

    public BarCodeServlet()
    {
    }

    public void init()
        throws ServletException
    {
    }

    private BarCode getChart(HttpServletRequest httpservletrequest)
    {
        if(barcode == null)
            barcode = new BarCode();
        try
        {
            setParameter("barType", httpservletrequest.getParameter("barType"));
            if(httpservletrequest.getParameter("width") != null && httpservletrequest.getParameter("height") != null)
            {
                setParameter("width", httpservletrequest.getParameter("width"));
                setParameter("height", httpservletrequest.getParameter("height"));
                setParameter("autoSize", "n");
            }
            setParameter("code", httpservletrequest.getParameter("code"));
            setParameter("st", httpservletrequest.getParameter("st"));
            setParameter("textFont", httpservletrequest.getParameter("textFont"));
            setParameter("fontColor", httpservletrequest.getParameter("fontColor"));
            setParameter("barColor", httpservletrequest.getParameter("barColor"));
            setParameter("backColor", httpservletrequest.getParameter("backColor"));
            setParameter("rotate", httpservletrequest.getParameter("rotate"));
            setParameter("barHeightCM", httpservletrequest.getParameter("barHeightCM"));
            setParameter("x", httpservletrequest.getParameter("x"));
            setParameter("n", httpservletrequest.getParameter("n"));
            setParameter("leftMarginCM", httpservletrequest.getParameter("leftMarginCM"));
            setParameter("topMarginCM", httpservletrequest.getParameter("topMarginCM"));
            setParameter("checkCharacter", httpservletrequest.getParameter("checkCharacter"));
            setParameter("checkCharacterInText", httpservletrequest.getParameter("checkCharacterInText"));
            setParameter("Code128Set", httpservletrequest.getParameter("Code128Set"));
            setParameter("UPCESytem", httpservletrequest.getParameter("UPCESytem"));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            barcode.code = "Parameter Error";
        }
        return barcode;
    }

    public void setParameter(String s, String s1)
    {
        if(s1 != null)
            if(s.equals("code"))
                barcode.code = s1;
            else
            if(s.equals("width"))
                barcode.width = (new Integer(s1)).intValue();
            else
            if(s.equals("height"))
                barcode.height = (new Integer(s1)).intValue();
            else
            if(s.equals("autoSize"))
                barcode.autoSize = s1.equalsIgnoreCase("y");
            else
            if(s.equals("st"))
                barcode.showText = s1.equalsIgnoreCase("y");
            else
            if(s.equals("textFont"))
                barcode.textFont = convertFont(s1);
            else
            if(s.equals("fontColor"))
                barcode.fontColor = convertColor(s1);
            else
            if(s.equals("barColor"))
                barcode.barColor = convertColor(s1);
            else
            if(s.equals("backColor"))
                barcode.backColor = convertColor(s1);
            else
            if(s.equals("rotate"))
                barcode.rotate = (new Integer(s1)).intValue();
            else
            if(s.equals("barHeightCM"))
                barcode.barHeightCM = (new Double(s1)).doubleValue();
            else
            if(s.equals("x"))
                barcode.X = (new Double(s1)).doubleValue();
            else
            if(s.equals("n"))
                barcode.N = (new Double(s1)).doubleValue();
            else
            if(s.equals("leftMarginCM"))
                barcode.leftMarginCM = (new Double(s1)).doubleValue();
            else
            if(s.equals("topMarginCM"))
                barcode.topMarginCM = (new Double(s1)).doubleValue();
            else
            if(s.equals("checkCharacter"))
                barcode.checkCharacter = s1.equalsIgnoreCase("y");
            else
            if(s.equals("checkCharacterInText"))
                barcode.checkCharacterInText = s1.equalsIgnoreCase("y");
            else
            if(s.equals("Code128Set"))
                barcode.Code128Set = s1.charAt(0);
            else
            if(s.equals("UPCESytem"))
                barcode.UPCESytem = s1.charAt(0);
            else
            if(s.equals("barType"))
                if(s1.equalsIgnoreCase("CODE39"))
                    barcode.barType = 0;
                else
                if(s1.equalsIgnoreCase("CODE39EXT"))
                    barcode.barType = 1;
                else
                if(s1.equalsIgnoreCase("INTERLEAVED25"))
                    barcode.barType = 2;
                else
                if(s1.equalsIgnoreCase("CODE11"))
                    barcode.barType = 3;
                else
                if(s1.equalsIgnoreCase("CODABAR"))
                    barcode.barType = 4;
                else
                if(s1.equalsIgnoreCase("MSI"))
                    barcode.barType = 5;
                else
                if(s1.equalsIgnoreCase("UPCA"))
                    barcode.barType = 6;
                else
                if(s1.equalsIgnoreCase("IND25"))
                    barcode.barType = 7;
                else
                if(s1.equalsIgnoreCase("MAT25"))
                    barcode.barType = 8;
                else
                if(s1.equalsIgnoreCase("CODE93"))
                    barcode.barType = 9;
                else
                if(s1.equalsIgnoreCase("EAN13"))
                    barcode.barType = 10;
                else
                if(s1.equalsIgnoreCase("EAN8"))
                    barcode.barType = 11;
                else
                if(s1.equalsIgnoreCase("UPCE"))
                    barcode.barType = 12;
                else
                if(s1.equalsIgnoreCase("CODE128"))
                    barcode.barType = 13;
                else
                if(s1.equalsIgnoreCase("CODE93EXT"))
                    barcode.barType = 14;
                else
                if(s1.equalsIgnoreCase("POSTNET"))
                    barcode.barType = 15;
                else
                if(s1.equalsIgnoreCase("PLANET"))
                    barcode.barType = 16;
                else
                if(s1.equalsIgnoreCase("UCC128"))
                    barcode.barType = 17;
    }

    /**
     * 产生条形码,详细参数见类注释。
     */
    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        httpservletresponse.setContentType("image/jpeg");
        javax.servlet.ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
        httpservletresponse.setHeader("Pragma", "no-cache");
        httpservletresponse.setHeader("Cache-Control", "no-cache");
        httpservletresponse.setDateHeader("Expires", 0L);
        try
        {
            BarCode barcode1 = getChart(httpservletrequest);
            barcode1.setSize(barcode1.width, barcode1.height);
            if(barcode1.autoSize)
            {
                BufferedImage bufferedimage = new BufferedImage(barcode1.getSize().width, barcode1.getSize().height, 13);
                java.awt.Graphics2D graphics2d = bufferedimage.createGraphics();
                barcode1.paint(graphics2d);
                barcode1.invalidate();
                graphics2d.dispose();
            }
            BufferedImage bufferedimage1 = new BufferedImage(barcode1.getSize().width, barcode1.getSize().height, 1);
            java.awt.Graphics2D graphics2d1 = bufferedimage1.createGraphics();
            barcode1.paint(graphics2d1);
            JPEGImageEncoder jpegimageencoder = JPEGCodec.createJPEGEncoder(servletoutputstream);
            JPEGEncodeParam jpegencodeparam = jpegimageencoder.getDefaultJPEGEncodeParam(bufferedimage1);
            jpegencodeparam.setQuality(1.0F, true);
            jpegimageencoder.setJPEGEncodeParam(jpegencodeparam);
            jpegimageencoder.encode(bufferedimage1, jpegencodeparam);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * 产生条形码,详细参数见类注释。
     */
    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException
    {
        try
        {
            doGet(httpservletrequest, httpservletresponse);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private Font convertFont(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s, "|");
        String s1 = stringtokenizer.nextToken();
        String s2 = stringtokenizer.nextToken();
        String s3 = stringtokenizer.nextToken();
        byte byte0 = -1;
        if(s2.trim().toUpperCase().equals("PLAIN"))
            byte0 = 0;
        else
        if(s2.trim().toUpperCase().equals("BOLD"))
            byte0 = 1;
        else
        if(s2.trim().toUpperCase().equals("ITALIC"))
            byte0 = 2;
        return new Font(s1, byte0, (new Integer(s3)).intValue());
    }

    private Color convertColor(String s)
    {
        Color color = null;
        if(s.trim().toUpperCase().equals("RED"))
            color = Color.red;
        else
        if(s.trim().toUpperCase().equals("BLACK"))
            color = Color.black;
        else
        if(s.trim().toUpperCase().equals("BLUE"))
            color = Color.blue;
        else
        if(s.trim().toUpperCase().equals("CYAN"))
            color = Color.cyan;
        else
        if(s.trim().toUpperCase().equals("DARKGRAY"))
            color = Color.darkGray;
        else
        if(s.trim().toUpperCase().equals("GRAY"))
            color = Color.gray;
        else
        if(s.trim().toUpperCase().equals("GREEN"))
            color = Color.green;
        else
        if(s.trim().toUpperCase().equals("LIGHTGRAY"))
            color = Color.lightGray;
        else
        if(s.trim().toUpperCase().equals("MAGENTA"))
            color = Color.magenta;
        else
        if(s.trim().toUpperCase().equals("ORANGE"))
            color = Color.orange;
        else
        if(s.trim().toUpperCase().equals("PINK"))
            color = Color.pink;
        else
        if(s.trim().toUpperCase().equals("WHITE"))
            color = Color.white;
        else
        if(s.trim().toUpperCase().equals("YELLOW"))
            color = Color.yellow;
        return color;
    }
}
