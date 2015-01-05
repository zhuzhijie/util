// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ImgCreator.java

package com.jing.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImgCreator
{

    private Image img;
    public Graphics g;

    public ImgCreator()
    {
    }

    public Image getImage(int i, int j)
    {
        int k = j <= i ? i : j;
        img = new BufferedImage(k, k, 13);
        g = ((BufferedImage)img).createGraphics();
        return img;
    }

    public Graphics getGraphics()
    {
        return g;
    }
}
