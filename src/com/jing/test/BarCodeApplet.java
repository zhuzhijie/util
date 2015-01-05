// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BarCodeApplet.java

package com.jing.test;

import java.applet.Applet;
import java.awt.*;
import java.util.StringTokenizer;

import com.jing.image.BarCode;


// Referenced classes of package com.mask.util.barcode:
//            BarCode

public class BarCodeApplet extends Applet
{

    public BarCode barcode;

    public BarCodeApplet()
    {
        barcode = null;
        setLayout(new BorderLayout());
    }

    public void start()
    {
        barcode.autoSize = false;
        barcode.paint(getGraphics());
    }

    public void refresh()
    {
        barcode.paint(barcode.getGraphics());
        paintAll(getGraphics());
    }

    public void init()
    {
        if(barcode == null)
            barcode = new BarCode();
        add("Center", barcode);
        initParameter();
    }

    private void initParameter()
    {
        try
        {
            setParameter("barType", getParameter("barType"));
            setParameter("code", getParameter("barcode"));
            setParameter("st", getParameter("st"));
            setParameter("textFont", getParameter("textFont"));
            setParameter("fontColor", getParameter("fontColor"));
            setParameter("barColor", getParameter("barColor"));
            setParameter("backColor", getParameter("backColor"));
            setParameter("rotate", getParameter("rotate"));
            setParameter("barHeightCM", getParameter("barHeightCM"));
            setParameter("x", getParameter("x"));
            setParameter("n", getParameter("n"));
            setParameter("leftMarginCM", getParameter("leftMarginCM"));
            setParameter("topMarginCM", getParameter("topMarginCM"));
            setParameter("checkCharacter", getParameter("checkCharacter"));
            setParameter("checkCharacterInText", getParameter("checkCharacterInText"));
            setParameter("Code128Set", getParameter("Code128Set"));
            setParameter("UPCESytem", getParameter("UPCESytem"));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            barcode.code = "Parameter Error";
        }
    }

    public void setParameter(String s, String s1)
    {
        if(s1 != null)
            if(s.equals("code"))
                barcode.code = s1;
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
