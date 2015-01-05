// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BarCode.java

package com.jing.image;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.Serializable;

// Referenced classes of package com.mask.util.barcode:
//            ImgCreator

public class BarCode extends Canvas
    implements Serializable
{

    public static final int CODE39 = 0;
    public static final int CODE39EXT = 1;
    public static final int INTERLEAVED25 = 2;
    public static final int CODE11 = 3;
    public static final int CODABAR = 4;
    public static final int MSI = 5;
    public static final int UPCA = 6;
    public static final int IND25 = 7;
    public static final int MAT25 = 8;
    public static final int CODE93 = 9;
    public static final int EAN13 = 10;
    public static final int EAN8 = 11;
    public static final int UPCE = 12;
    public static final int CODE128 = 13;
    public static final int CODE93EXT = 14;
    public static final int POSTNET = 15;
    public static final int PLANET = 16;
    public static final int UCC128 = 17;
    public int barType;
    public String code;
    public boolean autoSize;
    public int width;
    public int height;
    public boolean showText;
    public Font textFont;
    public Color fontColor;
    public Color barColor;
    public Color backColor;
    public int rotate;
    public double barHeightCM;
    public double X;
    public double N;
    public double leftMarginCM;
    public double topMarginCM;
    public boolean checkCharacter;
    public boolean checkCharacterInText;
    public char Code128Set;
    public char UPCESytem;
    private String qWe;
    public double wEr;
    public double eRt;
    protected int rTy;
    protected int tYu;
    private int yUi;
    private int uIo;
    private int iOp;
    private int oPa;
    private int pAs;
    private int aSd;
    private int sDf;
    private int dFg;
    public String fGh;
    public boolean gHj;
    public String hJk;
    protected int jKl;
    protected int kLz;
    protected double lZx;
    protected double zXc;
    public int xCv;
    protected int cVb;
    private int vBn;
    public char bNm;
    public char nMq;
    public boolean mQw;
    public boolean mNb;
    public double nBv;
    public double bVc;
    public double vCx;
    public double cXz;
    public double xZl;
    protected int zLk;
    protected int lKj;
    protected String kJh[][] = {
        {
            "0", "nnnwwnwnn"
        }, {
            "1", "wnnwnnnnw"
        }, {
            "2", "nnwwnnnnw"
        }, {
            "3", "wnwwnnnnn"
        }, {
            "4", "nnnwwnnnw"
        }, {
            "5", "wnnwwnnnn"
        }, {
            "6", "nnwwwnnnn"
        }, {
            "7", "nnnwnnwnw"
        }, {
            "8", "wnnwnnwnn"
        }, {
            "9", "nnwwnnwnn"
        }, {
            "A", "wnnnnwnnw"
        }, {
            "B", "nnwnnwnnw"
        }, {
            "C", "wnwnnwnnn"
        }, {
            "D", "nnnnwwnnw"
        }, {
            "E", "wnnnwwnnn"
        }, {
            "F", "nnwnwwnnn"
        }, {
            "G", "nnnnnwwnw"
        }, {
            "H", "wnnnnwwnn"
        }, {
            "I", "nnwnnwwnn"
        }, {
            "J", "nnnnwwwnn"
        }, {
            "K", "wnnnnnnww"
        }, {
            "L", "nnwnnnnww"
        }, {
            "M", "wnwnnnnwn"
        }, {
            "N", "nnnnwnnww"
        }, {
            "O", "wnnnwnnwn"
        }, {
            "P", "nnwnwnnwn"
        }, {
            "Q", "nnnnnnwww"
        }, {
            "R", "wnnnnnwwn"
        }, {
            "S", "nnwnnnwwn"
        }, {
            "T", "nnnnwnwwn"
        }, {
            "U", "wwnnnnnnw"
        }, {
            "V", "nwwnnnnnw"
        }, {
            "W", "wwwnnnnnn"
        }, {
            "X", "nwnnwnnnw"
        }, {
            "Y", "wwnnwnnnn"
        }, {
            "Z", "nwwnwnnnn"
        }, {
            "-", "nwnnnnwnw"
        }, {
            ".", "wwnnnnwnn"
        }, {
            " ", "nwwnnnwnn"
        }, {
            "$", "nwnwnwnnn"
        }, {
            "/", "nwnwnnnwn"
        }, {
            "+", "nwnnnwnwn"
        }, {
            "%", "nnnwnwnwn"
        }, {
            "*", "nwnnwnwnn"
        }
    };
    protected String jHg[][] = {
        {
            "0", "nnwwn"
        }, {
            "1", "wnnnw"
        }, {
            "2", "nwnnw"
        }, {
            "3", "wwnnn"
        }, {
            "4", "nnwnw"
        }, {
            "5", "wnwnn"
        }, {
            "6", "nwwnn"
        }, {
            "7", "nnnww"
        }, {
            "8", "wnnwn"
        }, {
            "9", "nwnwn"
        }
    };
    protected String hGf[][] = {
        {
            "0", "nwnwnwnw"
        }, {
            "1", "nwnwnwwn"
        }, {
            "2", "nwnwwnnw"
        }, {
            "3", "nwnwwnwn"
        }, {
            "4", "nwwnnwnw"
        }, {
            "5", "nwwnnwwn"
        }, {
            "6", "nwwnwnnw"
        }, {
            "7", "nwwnwnwn"
        }, {
            "8", "wnnwnwnw"
        }, {
            "9", "wnnwnwwn"
        }
    };
    protected String gFd[][] = {
        {
            "0", "nnnnw"
        }, {
            "1", "wnnnw"
        }, {
            "2", "nwnnw"
        }, {
            "3", "wwnnn"
        }, {
            "4", "nnwnw"
        }, {
            "5", "wnwnn"
        }, {
            "6", "nwwnn"
        }, {
            "7", "nnnww"
        }, {
            "8", "wnnwn"
        }, {
            "9", "wnnnn"
        }, {
            "-", "nnwnn"
        }
    };
    protected String fDs[][] = {
        {
            "0", "nnnnnww"
        }, {
            "1", "nnnnwwn"
        }, {
            "2", "nnnwnnw"
        }, {
            "3", "wwnnnnn"
        }, {
            "4", "nnwnnwn"
        }, {
            "5", "wnnnnwn"
        }, {
            "6", "nwnnnnw"
        }, {
            "7", "nwnnwnn"
        }, {
            "8", "nwwnnnn"
        }, {
            "9", "wnnwnnn"
        }, {
            "-", "nnnwwnn"
        }, {
            "$", "nnwwnnn"
        }, {
            ":", "wnnnwnw"
        }, {
            "/", "wnwnnnw"
        }, {
            ".", "wnwnwnn"
        }, {
            "+", "nnwnwnw"
        }, {
            "A", "nnwwnwn"
        }, {
            "B", "nwnwnnw"
        }, {
            "C", "nnnwnww"
        }, {
            "D", "nnnwwwn"
        }
    };
    protected String dSa[][] = {
        {
            "0", "131112"
        }, {
            "1", "111213"
        }, {
            "2", "111312"
        }, {
            "3", "111411"
        }, {
            "4", "121113"
        }, {
            "5", "121212"
        }, {
            "6", "121311"
        }, {
            "7", "111114"
        }, {
            "8", "131211"
        }, {
            "9", "141111"
        }, {
            "A", "211113"
        }, {
            "B", "211212"
        }, {
            "C", "211311"
        }, {
            "D", "221112"
        }, {
            "E", "221211"
        }, {
            "F", "231111"
        }, {
            "G", "112113"
        }, {
            "H", "112212"
        }, {
            "I", "112311"
        }, {
            "J", "122112"
        }, {
            "K", "132111"
        }, {
            "L", "111123"
        }, {
            "M", "111222"
        }, {
            "N", "111321"
        }, {
            "O", "121122"
        }, {
            "P", "131121"
        }, {
            "Q", "212112"
        }, {
            "R", "212211"
        }, {
            "S", "211122"
        }, {
            "T", "211221"
        }, {
            "U", "221121"
        }, {
            "V", "222111"
        }, {
            "W", "112122"
        }, {
            "X", "112221"
        }, {
            "Y", "122121"
        }, {
            "Z", "123111"
        }, {
            "-", "121131"
        }, {
            ".", "311112"
        }, {
            " ", "311211"
        }, {
            "$", "321111"
        }, {
            "/", "112131"
        }, {
            "+", "113121"
        }, {
            "%", "211131"
        }, {
            "_1", "121221"
        }, {
            "_2", "312111"
        }, {
            "_3", "311121"
        }, {
            "_4", "122211"
        }
    };
    protected String sAp[][] = {
        {
            "0", "3211"
        }, {
            "1", "2221"
        }, {
            "2", "2122"
        }, {
            "3", "1411"
        }, {
            "4", "1132"
        }, {
            "5", "1231"
        }, {
            "6", "1114"
        }, {
            "7", "1312"
        }, {
            "8", "1213"
        }, {
            "9", "3112"
        }
    };
    protected String aPo[][] = {
        {
            "0", "3211"
        }, {
            "1", "2221"
        }, {
            "2", "2122"
        }, {
            "3", "1411"
        }, {
            "4", "1132"
        }, {
            "5", "1231"
        }, {
            "6", "1114"
        }, {
            "7", "1312"
        }, {
            "8", "1213"
        }, {
            "9", "3112"
        }
    };
    protected String pOi[][] = {
        {
            "0", "3211"
        }, {
            "1", "2221"
        }, {
            "2", "2122"
        }, {
            "3", "1411"
        }, {
            "4", "1132"
        }, {
            "5", "1231"
        }, {
            "6", "1114"
        }, {
            "7", "1312"
        }, {
            "8", "1213"
        }, {
            "9", "3112"
        }
    };
    protected String oIu[][] = {
        {
            "0", "1123"
        }, {
            "1", "1222"
        }, {
            "2", "2212"
        }, {
            "3", "1141"
        }, {
            "4", "2311"
        }, {
            "5", "1321"
        }, {
            "6", "4111"
        }, {
            "7", "2131"
        }, {
            "8", "3121"
        }, {
            "9", "2113"
        }
    };
    protected String iUy[] = {
        "%U", "$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I", 
        "$J", "$K", "$L", "$M", "$N", "$O", "$P", "$Q", "$R", "$S", 
        "$T", "$U", "$V", "$W", "$X", "$Y", "$Z", "%A", "%B", "%C", 
        "%D", "%E", " ", "/A", "/B", "/C", "/D", "/E", "/F", "/G", 
        "/H", "/I", "/J", "/K", "/L", "-", ".", "/O", "0", "1", 
        "2", "3", "4", "5", "6", "7", "8", "9", "/Z", "%F", 
        "%G", "%H", "%I", "%J", "%V", "A", "B", "C", "D", "E", 
        "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", 
        "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", 
        "Z", "%K", "%L", "%M", "%N", "%O", "%W", "+A", "+B", "+C", 
        "+D", "+E", "+F", "+G", "+H", "+I", "+J", "+K", "+L", "+M", 
        "+N", "+O", "+P", "+Q", "+R", "+S", "+T", "+U", "+V", "+W", 
        "+X", "+Y", "+Z", "%P", "%Q", "%R", "%S", "%T"
    };
    protected String uYt[] = {
        "_2U", "_1A", "_1B", "_1C", "_1D", "_1E", "_1F", "_1G", "_1H", "_1I", 
        "_1J", "_1K", "_1L", "_1M", "_1N", "_1O", "_1P", "_1Q", "_1R", "_1S", 
        "_1T", "_1U", "_1V", "_1W", "_1X", "_1Y", "_1Z", "_2A", "_2B", "_2C", 
        "_2D", "_2E", " ", "_3A", "_3B", "_3C", "_3D", "_3E", "_3F", "_3G", 
        "_3H", "_3I", "_3J", "_3K", "_3L", "-", ".", "_3O", "0", "1", 
        "2", "3", "4", "5", "6", "7", "8", "9", "_3Z", "_2F", 
        "_2G", "_2H", "_2I", "_2J", "_2V", "A", "B", "C", "D", "E", 
        "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", 
        "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", 
        "Z", "_2K", "_2L", "_2M", "_2N", "_2O", "_2W", "_4A", "_4B", "_4C", 
        "_4D", "_4E", "_4F", "_4G", "_4H", "_4I", "_4J", "_4K", "_4L", "_4M", 
        "_4N", "_4O", "_4P", "_4Q", "_4R", "_4S", "_4T", "_4U", "_4V", "_4W", 
        "_4X", "_4Y", "_4Z", "_2P", "_2Q", "_2R", "_2S", "_2T"
    };
    protected byte yTr[] = {
        112, 111, 119, 101, 114, 32, 98, 121, 32, 109, 
        97, 115, 107
    };
    protected String tRe[] = {
        "EEEOOO", "EEOEOO", "EEOOEO", "EEOOOE", "EOEEOO", "EOOEEO", "EOOOEE", "EOEOEO", "EOEOOE", "EOOEOE"
    };
    protected String rEw[] = {
        "OOOEEE", "OOEOEE", "OOEEOE", "OOEEEO", "OEOOEE", "OEEOOE", "OEEEOO", "OEOEOE", "OEOEEO", "OEEOEO"
    };
    protected String eWq[][] = {
        {
            "0", "3211"
        }, {
            "1", "2221"
        }, {
            "2", "2122"
        }, {
            "3", "1411"
        }, {
            "4", "1132"
        }, {
            "5", "1231"
        }, {
            "6", "1114"
        }, {
            "7", "1312"
        }, {
            "8", "1213"
        }, {
            "9", "3112"
        }
    };
    protected String wQm[][] = {
        {
            "0", "1123"
        }, {
            "1", "1222"
        }, {
            "2", "2212"
        }, {
            "3", "1141"
        }, {
            "4", "2311"
        }, {
            "5", "1321"
        }, {
            "6", "4111"
        }, {
            "7", "2131"
        }, {
            "8", "3121"
        }, {
            "9", "2113"
        }
    };
    protected String qMn[][] = {
        {
            "0", "3211"
        }, {
            "1", "2221"
        }, {
            "2", "2122"
        }, {
            "3", "1411"
        }, {
            "4", "1132"
        }, {
            "5", "1231"
        }, {
            "6", "1114"
        }, {
            "7", "1312"
        }, {
            "8", "1213"
        }, {
            "9", "3112"
        }
    };
    protected String q1w[] = {
        "AAAAA", "ABABB", "ABBAB", "ABBBA", "BAABB", "BBAAB", "BBBAA", "BABAB", "BABBA", "BBABA"
    };
    protected String w2e[] = {
        "EEOOO", "EOEOO", "EOOEO", "EOOOE", "OEEOO", "OOEEO", "OOOEE", "OEOEO", "OEOOE", "OOEOE"
    };
    protected String e3r[] = {
        "212222", "222122", "222221", "121223", "121322", "131222", "122213", "122312", "132212", "221213", 
        "221312", "231212", "112232", "122132", "122231", "113222", "123122", "123221", "223211", "221132", 
        "221231", "213212", "223112", "312131", "311222", "321122", "321221", "312212", "322112", "322211", 
        "212123", "212321", "232121", "111323", "131123", "131321", "112313", "132113", "132311", "211313", 
        "231113", "231311", "112133", "112331", "132131", "113123", "113321", "133121", "313121", "211331", 
        "231131", "213113", "213311", "213131", "311123", "311321", "331121", "312113", "312311", "332111", 
        "314111", "221411", "431111", "111224", "111422", "121124", "121421", "141122", "141221", "112214", 
        "112412", "122114", "122411", "142112", "142211", "241211", "221114", "413111", "241112", "134111", 
        "111242", "121142", "121241", "114212", "124112", "124211", "411212", "421112", "421211", "212141", 
        "214121", "412121", "111143", "111341", "131141", "114113", "114311", "411113", "411311", "113141", 
        "114131", "311141", "411131"
    };
    protected String r4t[] = {
        " ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", 
        "*", "+", ",", "-", ".", "/", "0", "1", "2", "3", 
        "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", 
        ">", "?", "@", "A", "B", "C", "D", "E", "F", "G", 
        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", 
        "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", 
        "\\", "]", "^", "_", "`", "a", "b", "c", "d", "e", 
        "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", 
        "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", 
        "z", "{", "|", "}", "~", "\303", "\304", "\305", "\306", "\307", 
        "\310", "\311", "\312"
    };
    protected String t5y[] = {
        "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", 
        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", 
        "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", 
        "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", 
        "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", 
        "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", 
        "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", 
        "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", 
        "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", 
        "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", 
        "\310\310", "\311\311", "\312\312"
    };
    protected String y6u[][] = {
        {
            "0", "11000"
        }, {
            "1", "00011"
        }, {
            "2", "00101"
        }, {
            "3", "00110"
        }, {
            "4", "01001"
        }, {
            "5", "01010"
        }, {
            "6", "01100"
        }, {
            "7", "10001"
        }, {
            "8", "10010"
        }, {
            "9", "10100"
        }
    };
    protected String u7i[][] = {
        {
            "0", "00111"
        }, {
            "1", "11100"
        }, {
            "2", "11010"
        }, {
            "3", "11001"
        }, {
            "4", "10110"
        }, {
            "5", "10101"
        }, {
            "6", "10011"
        }, {
            "7", "01110"
        }, {
            "8", "01101"
        }, {
            "9", "01011"
        }
    };

    public BarCode()
    {
        barType = 13;
        code = "BATISTUTA";
        autoSize = true;
        width = 1;
        height = 1;
        showText = true;
        textFont = new Font("Arial", 0, 11);
        fontColor = Color.black;
        barColor = Color.black;
        backColor = Color.white;
        rotate = 0;
        barHeightCM = 1.0D;
        X = 0.029999999999999999D;
        N = 2D;
        leftMarginCM = 0.29999999999999999D;
        topMarginCM = 0.20000000000000001D;
        checkCharacter = true;
        checkCharacterInText = true;
        Code128Set = '0';
        UPCESytem = '0';
        qWe = "";
        wEr = 0.29999999999999999D;
        eRt = 0.125D;
        rTy = 0;
        tYu = 0;
        yUi = 0;
        uIo = 0;
        iOp = 0;
        oPa = 0;
        pAs = 0;
        fGh = "";
        gHj = true;
        hJk = "";
        jKl = 0;
        kLz = 0;
        lZx = 0.0D;
        zXc = 0.0D;
        xCv = 38;
        cVb = 0;
        vBn = 0;
        bNm = 'A';
        nMq = 'B';
        mQw = false;
        mNb = false;
        nBv = 1.0D;
        bVc = 0.45000000000000001D;
        vCx = 0.0D;
        cXz = 0.5D;
        xZl = 0.80000000000000004D;
        zLk = 0;
        lKj = 0;
    }

    public void setSymbologyID(int i)
    {
        barType = i;
        invalidate();
    }

    public int getSymbologyID()
    {
        return barType;
    }

    public void setDataToEncode(String s)
    {
        code = s;
        invalidate();
    }

    public String getDataToEncode()
    {
        return code;
    }

    public void setCheckCharacter(boolean flag)
    {
        checkCharacter = flag;
        invalidate();
    }

    public boolean getCheckCharacter()
    {
        return checkCharacter;
    }

    public void setCheckCharacterInText(boolean flag)
    {
        checkCharacterInText = flag;
        invalidate();
    }

    public boolean getCheckCharacterInText()
    {
        return checkCharacterInText;
    }

    public void setWEr(double d)
    {
        wEr = d;
        invalidate();
    }

    public double getWEr()
    {
        return wEr;
    }

    public void setERt(double d)
    {
        eRt = d;
        invalidate();
    }

    public double getERt()
    {
        return eRt;
    }

    public void setLeftMarginCM(double d)
    {
        leftMarginCM = d;
        invalidate();
    }

    public double getLeftMarginCM()
    {
        return leftMarginCM;
    }

    public void setTopMarginCM(double d)
    {
        topMarginCM = d;
        invalidate();
    }

    public double getTopMarginCM()
    {
        return topMarginCM;
    }

    public void setFGh(String s)
    {
        fGh = s;
        invalidate();
    }

    public String getFGh()
    {
        return fGh;
    }

    public void setBackground(Color color)
    {
        backColor = color;
        invalidate();
    }

    public Color getBackground()
    {
        return backColor;
    }

    public void setXCv(int i)
    {
        xCv = i;
        invalidate();
    }

    public int getXCv()
    {
        return xCv;
    }

    public void setBarHeightCM(double d)
    {
        barHeightCM = d;
        invalidate();
    }

    public double getBarHeightCM()
    {
        return barHeightCM;
    }

    public void setAutoSize(boolean flag)
    {
        autoSize = flag;
        invalidate();
    }

    public boolean getAutoSize()
    {
        return autoSize;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }

    public Dimension getMinimumSize()
    {
        Dimension dimension = new Dimension(10, 10);
        return dimension;
    }

    public void setShowText(boolean flag)
    {
        showText = flag;
        invalidate();
    }

    public boolean getShowText()
    {
        return showText;
    }

    public void setFont(Font font)
    {
        textFont = font;
        invalidate();
    }

    public Font getFont()
    {
        return textFont;
    }

    public void setTextFontColor(Color color)
    {
        fontColor = color;
        invalidate();
    }

    public Color getTextFontColor()
    {
        return fontColor;
    }

    public void setForeground(Color color)
    {
        barColor = color;
        invalidate();
    }

    public Color getForeground()
    {
        return barColor;
    }

    public void setUPCESytem(String s)
    {
        if(s.equals("0"))
            UPCESytem = '0';
        if(s.equals("1"))
            UPCESytem = '1';
        invalidate();
    }

    public String getUPCESytem()
    {
        String s = "";
        if(UPCESytem == '0')
            s = "0";
        if(UPCESytem == '1')
            s = "1";
        return s;
    }

    public void setBNm(String s)
    {
        if(s.equals("A"))
            bNm = 'A';
        if(s.equals("B"))
            bNm = 'B';
        if(s.equals("C"))
            bNm = 'C';
        if(s.equals("D"))
            bNm = 'D';
        invalidate();
    }

    public String getBNm()
    {
        String s = "";
        if(bNm == 'A')
            s = "A";
        if(bNm == 'B')
            s = "B";
        if(bNm == 'C')
            s = "C";
        if(bNm == 'D')
            s = "D";
        return s;
    }

    public void setNMq(String s)
    {
        if(s.equals("A"))
            nMq = 'A';
        if(s.equals("B"))
            nMq = 'B';
        if(s.equals("C"))
            nMq = 'C';
        if(s.equals("D"))
            nMq = 'D';
        invalidate();
    }

    public String getNMq()
    {
        String s = "";
        if(nMq == 'A')
            s = "A";
        if(nMq == 'B')
            s = "B";
        if(nMq == 'C')
            s = "C";
        if(nMq == 'D')
            s = "D";
        return s;
    }

    public void setMQw(boolean flag)
    {
        mQw = flag;
        invalidate();
    }

    public boolean getMQw()
    {
        return mQw;
    }

    public void setMNb(boolean flag)
    {
        mNb = flag;
        invalidate();
    }

    public boolean getMNb()
    {
        return mNb;
    }

    public void setCode128Set(String s)
    {
        if(s.toUpperCase().equals("A"))
            Code128Set = 'A';
        if(s.toUpperCase().equals("B"))
            Code128Set = 'B';
        if(s.toUpperCase().equals("C"))
            Code128Set = 'C';
        if(s.toUpperCase().equals("AUTO"))
            Code128Set = '0';
        if(s.toUpperCase().equals("0"))
            Code128Set = '0';
        invalidate();
    }

    public String getCode128Set()
    {
        String s = "";
        if(Code128Set == 'A')
            s = "A";
        if(Code128Set == 'B')
            s = "B";
        if(Code128Set == 'C')
            s = "C";
        if(Code128Set == '0')
            s = "0";
        return s;
    }

    public void setXDimensionCM(double d)
    {
        X = d;
        invalidate();
    }

    public double getXDimensionCM()
    {
        return X;
    }

    public void setNarrowToWideRatio(double d)
    {
        N = d;
        invalidate();
    }

    public double getNarrowToWideRatio()
    {
        return N;
    }

    public void setRotationAngle(int i)
    {
        rotate = i;
        invalidate();
    }

    public int getRotationAngle()
    {
        return rotate;
    }

    protected void qwer(Graphics g, int i, boolean flag, int j)
    {
        if(flag)
        {
            g.setColor(barColor);
            g.fillRect(zLk, tYu + j, i, (cVb + vBn) - j);
        }
        zLk = zLk + i;
    }

    protected void wert(Graphics g, String s)
    {
        int i = (int)(wEr * (double)xCv);
        int j = (int)(eRt * (double)xCv);
        g.setColor(barColor);
        for(int k = 0; k < s.length(); k++)
        {
            char c = s.charAt(k);
            if(c == '0')
                g.fillRect(zLk, tYu, jKl, j + vBn);
            if(c == '1')
                g.fillRect(zLk, tYu + (j - i), jKl, i + vBn);
            zLk = zLk + jKl;
            zLk = zLk + kLz;
        }

    }

    protected void erty(Graphics g)
    {
        int i = 0;
        String s = code;
        wert(g, "1");
        for(int j = code.length() - 1; j >= 0; j--)
        {
            String s1 = "" + code.charAt(j);
            i += rtyu(y6u, s1);
        }

        int k = (int)bnmq(i, 10D);
        if(k != 0)
            k = 10 - k;
        if(checkCharacter)
            s = s + (new Integer(k)).toString();
        for(int l = 0; l < s.length(); l++)
        {
            String s2 = "" + s.charAt(l);
            int i1 = rtyu(y6u, s2);
            wert(g, y6u[i1][1]);
        }

        wert(g, "1");
    }

    protected int rtyu(String as[][], String s)
    {
        for(int i = 0; i < as.length; i++)
            if(s.compareTo(as[i][0]) == 0)
                return i;

        return -1;
    }

    protected void tyui(Graphics g)
    {
        int i = 0;
        String s = code;
        wert(g, "1");
        for(int j = code.length() - 1; j >= 0; j--)
        {
            String s1 = "" + code.charAt(j);
            i += rtyu(u7i, s1);
        }

        int k = (int)bnmq(i, 10D);
        if(k != 0)
            k = 10 - k;
        if(checkCharacter)
            s = s + (new Integer(k)).toString();
        for(int l = 0; l < s.length(); l++)
        {
            String s2 = "" + s.charAt(l);
            int i1 = rtyu(u7i, s2);
            wert(g, u7i[i1][1]);
        }

        wert(g, "1");
    }

    protected void yuio(Graphics g)
    {
        String s = code;
        nbvc(g, "bwbw", "nnnn");
        if(bnmq(code.length(), 2D) == 0.0D && checkCharacter)
            s = "0" + code;
        if(bnmq(code.length(), 2D) == 1.0D && !checkCharacter)
            s = "0" + code;
        int i = 0;
        int j = 0;
        boolean flag = true;
        for(int k = s.length() - 1; k >= 0; k--)
        {
            String s1 = "" + s.charAt(k);
            if(flag)
                i += rtyu(jHg, s1);
            else
                j += rtyu(jHg, s1);
            flag = !flag;
        }

        int l = i * 3 + j;
        l = (int)bnmq(l, 10D);
        if(l != 0)
            l = 10 - l;
        if(checkCharacter)
            s = s + (new Integer(l)).toString();
        for(int i1 = 0; i1 < s.length(); i1 += 2)
        {
            String s2 = "" + s.charAt(i1);
            String s3 = "" + s.charAt(i1 + 1);
            int j1 = rtyu(jHg, s2);
            int k1 = rtyu(jHg, s3);
            for(int l1 = 0; l1 < 5; l1++)
            {
                nbvc(g, "b", "" + jHg[j1][1].charAt(l1));
                nbvc(g, "w", "" + jHg[k1][1].charAt(l1));
            }

        }

        nbvc(g, "bwb", "wnn");
        if(checkCharacterInText)
            hJk = s;
        else
            hJk = code;
    }

    protected void uiop(Graphics g)
    {
        String s = code;
        nbvc(g, "bwbwbw", "wwwwnw");
        int i = 0;
        int j = 0;
        boolean flag = true;
        for(int k = s.length() - 1; k >= 0; k--)
        {
            String s1 = "" + s.charAt(k);
            if(flag)
                i += rtyu(jHg, s1);
            else
                j += rtyu(jHg, s1);
            flag = !flag;
        }

        int l = i * 3 + j;
        l = (int)bnmq(l, 10D);
        if(l != 0)
            l = 10 - l;
        if(checkCharacter)
            s = s + (new Integer(l)).toString();
        for(int i1 = 0; i1 < s.length(); i1++)
        {
            String s2 = "" + s.charAt(i1);
            int j1 = rtyu(jHg, s2);
            if(j1 >= 0)
            {
                for(int k1 = 0; k1 < jHg[j1][1].length(); k1++)
                {
                    nbvc(g, "b", "" + jHg[j1][1].charAt(k1));
                    nbvc(g, "w", "w");
                }

            }
        }

        nbvc(g, "bwbwb", "wwnww");
    }

    protected String iopa(String s)
    {
        boolean flag = true;
        int i = 0;
        int j = 0;
        int k = 0;
        for(int l = s.length() - 1; l >= 0; l--)
        {
            if(flag)
                i += (new Integer("" + s.charAt(l))).intValue();
            else
                j += (new Integer("" + s.charAt(l))).intValue();
            flag = !flag;
        }

        j = i * 3 + j;
        k = (int)bnmq(j, 10D);
        if(k != 0)
            k = 10 - k;
        return "" + k;
    }

    protected void opas(Graphics g)
    {
        if(code.length() < 11)
            return;
        if(code.length() == 13)
        {
            fGh = code.substring(11, 13);
            mQw = true;
        }
        if(code.length() == 14)
        {
            fGh = code.substring(12, 14);
            mQw = true;
        }
        if(code.length() == 16)
        {
            fGh = code.substring(11, 16);
            mNb = true;
        }
        if(code.length() == 17)
        {
            fGh = code.substring(12, 17);
            mNb = true;
        }
        code = code.substring(0, 11);
        code = code + iopa(code);
        vcxz(g, "bwb", "nnn", 0);
        yUi = zLk;
        for(int i = 0; i < code.length(); i++)
        {
            String s = "" + code.charAt(i);
            if(i <= 5)
            {
                int j = rtyu(sAp, s);
                nbvc(g, "wbwb", sAp[j][1]);
            } else
            {
                int k = rtyu(aPo, s);
                nbvc(g, "bwbw", aPo[k][1]);
            }
            if(i == 5)
            {
                uIo = zLk;
                vcxz(g, "wbwbw", "nnnnn", 0);
                iOp = zLk;
            }
        }

        oPa = zLk;
        vcxz(g, "bwb", "nnn", 0);
        pAs = zLk;
        if(mQw)
            hjkl(g, fGh);
        else
        if(mNb)
            jklz(g, fGh);
    }

    protected void pasd(Graphics g)
    {
        int i = 0;
        if(code.length() < 12)
            return;
        if(code.length() == 14)
        {
            fGh = code.substring(12, 14);
            mQw = true;
        }
        if(code.length() == 15)
        {
            fGh = code.substring(13, 15);
            mQw = true;
        }
        if(code.length() == 17)
        {
            fGh = code.substring(12, 17);
            mNb = true;
        }
        if(code.length() == 18)
        {
            fGh = code.substring(13, 18);
            mNb = true;
        }
        code = code.substring(0, 12);
        code = code + iopa(code);
        vcxz(g, "bwb", "nnn", 0);
        yUi = zLk;
        String s = q1w[(new Integer("" + code.charAt(0))).intValue()];
        i = rtyu(eWq, "" + code.charAt(1));
        nbvc(g, "wbwb", eWq[i][1]);
        for(int j = 2; j < 12; j++)
        {
            String s1 = "" + code.charAt(j);
            i = -1;
            if(j <= 6)
            {
                String as[][] = eWq;
                if(s.charAt(j - 2) == 'B')
                    as = wQm;
                i = rtyu(as, s1);
                nbvc(g, "wbwb", as[i][1]);
            } else
            {
                i = rtyu(qMn, s1);
                nbvc(g, "bwbw", qMn[i][1]);
            }
            if(j == 6)
            {
                uIo = zLk;
                vcxz(g, "wbwbw", "nnnnn", 0);
                iOp = zLk;
            }
        }

        i = rtyu(qMn, "" + code.charAt(12));
        nbvc(g, "bwbw", qMn[i][1]);
        oPa = zLk;
        vcxz(g, "bwb", "nnn", 0);
        pAs = zLk;
        if(mQw)
            hjkl(g, fGh);
        else
        if(mNb)
            jklz(g, fGh);
    }

    private int asdf(String as[], String s)
    {
        for(int i = 0; i < as.length; i++)
            if(as[i].compareTo(s) == 0)
                return i;

        return -1;
    }

    protected void sdfg(Graphics g)
    {
        String s = code;
        hJk = code;
        int i = code.length();
        String as[] = r4t;
        int j = 103;
        if(Code128Set != '0')
        {
            s = "";
            hJk = "";
            for(int k = 1; k <= i; k++)
            {
                int i1 = code.charAt(k - 1);
                if(i1 < 32 && i1 >= 0)
                {
                    if(Code128Set == 'A')
                        s = s + (char)(i1 + 96);
                    if(Code128Set == 'B')
                        if(code.charAt(k) < ' ')
                        {
                            s = s + '\311' + (char)(i1 + 96) + (char)(code.charAt(k) + 96) + '\310';
                            k++;
                        } else
                        {
                            s = s + '\311' + (char)(i1 + 96) + '\310';
                        }
                    if(Code128Set == 'C')
                        if(code.charAt(k) < ' ')
                        {
                            s = s + '\311' + '\311' + (i1 + 64) + (code.charAt(k) + 64) + "99";
                            k++;
                        } else
                        {
                            s = s + '\311' + '\311' + (i1 + 64) + "99";
                        }
                    if(i1 == 13 || i1 == 9)
                        hJk = hJk + "  ";
                } else
                {
                    hJk = hJk + (char)i1;
                    s = s + (char)i1;
                }
            }

        }
        if(Code128Set == '0')
        {
            as = r4t;
            hJk = "";
            s = "";
            char c = '\314';
            byte byte0 = 66;
            char c1 = code.charAt(0);
            if(c1 < ' ')
                c = '\313';
            if(c1 > '\037' && c1 < '\177')
                c = '\314';
            if(i > 3 && c1 > '/' && c1 < ':' && code.charAt(1) > '/' && code.charAt(1) < ':' && code.charAt(2) > '/' && code.charAt(2) < ':' && code.charAt(3) > '/' && code.charAt(3) < ':')
                c = '\315';
            if(c1 == '\312' || c1 > '\323')
                c = '\315';
            if(c == '\313')
            {
                byte0 = 65;
                j = 103;
                nbvc(g, "bwbwbw", "211412");
            }
            if(c == '\314')
            {
                byte0 = 66;
                j = 104;
                nbvc(g, "bwbwbw", "211214");
            }
            if(c == '\315')
            {
                byte0 = 67;
                j = 105;
                nbvc(g, "bwbwbw", "211232");
            }
            for(int l1 = 1; l1 <= i; l1++)
            {
                int j2 = code.charAt(l1 - 1);
                if(l1 < i - 1 && (j2 == 202 || j2 > 211))
                    s = s + '\312';
                else
                if(l1 <= i - 3 && j2 > 47 && j2 < 58 && code.charAt(l1) > '/' && code.charAt(l1) < ':' && code.charAt(l1 + 1) > '/' && code.charAt(l1 + 1) < ':' && code.charAt(l1 + 2) > '/' && code.charAt(l1 + 2) < ':' || l1 <= i - 1 && j2 > 47 && j2 < 58 && code.charAt(l1) > '/' && code.charAt(l1) < ':' && byte0 == 67)
                {
                    if(byte0 != 67)
                    {
                        s = s + '\307';
                        byte0 = 67;
                    }
                    j2 = (code.charAt(l1 - 1) - 48) * 10 + (code.charAt(l1) - 48);
                    if(j2 < 95 && j2 >= 0)
                        s = s + (char)(j2 + 32);
                    else
                    if(j2 > 94)
                        s = s + (char)(j2 + 100);
                    l1++;
                } else
                if(l1 <= i && (j2 < 32 || byte0 == 65 && j2 < 96))
                {
                    if(byte0 != 65)
                    {
                        s = s + '\311';
                        byte0 = 65;
                    }
                    if(j2 < 32)
                        s = s + (char)(j2 + 96);
                    else
                    if(j2 > 31)
                        s = s + (char)j2;
                } else
                if(l1 <= i && j2 > 31 && j2 < 127)
                {
                    if(byte0 != 66)
                    {
                        s = s + '\310';
                        byte0 = 66;
                    }
                    s = s + (char)j2;
                }
            }

            for(int k2 = 1; k2 <= i; k2++)
            {
                char c2 = code.charAt(k2 - 1);
                boolean flag = false;
                if(k2 < i - 1 && (c2 == '\312' || c2 > '\323'))
                {
                    int i3 = (code.charAt(k2) - 48) * 10 + (code.charAt(k2 + 1) - 48);
                    if(code.charAt(k2 - 1) == '\324')
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + ") ";
                        k2 += 2;
                        flag = true;
                    } else
                    if(k2 < i - 2 && code.charAt(k2 - 1) == '\325')
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + code.charAt(k2 + 2) + ") ";
                        k2 += 3;
                        flag = true;
                    } else
                    if(k2 < i - 3 && code.charAt(k2 - 1) == '\326')
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + code.charAt(k2 + 2) + code.charAt(k2 + 3) + ") ";
                        k2 += 4;
                        flag = true;
                    } else
                    if(k2 < i - 4 && code.charAt(k2 - 1) == '\327')
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + code.charAt(k2 + 2) + code.charAt(k2 + 3) + code.charAt(k2 + 4) + ") ";
                        k2 += 5;
                        flag = true;
                    } else
                    if(i3 <= 30 && i3 >= 0 || i3 <= 99 && i3 >= 90)
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + ") ";
                        k2 += 2;
                        flag = true;
                    } else
                    if(k2 < i - 2 && (i3 <= 49 && i3 >= 40 || i3 <= 25 && i3 >= 23))
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + code.charAt(k2 + 2) + ") ";
                        k2 += 3;
                        flag = true;
                    } else
                    if(k2 < i - 3 && (i3 <= 81 && i3 >= 80 || i3 <= 34 && i3 >= 31))
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + code.charAt(k2 + 2) + code.charAt(k2 + 3) + ") ";
                        k2 += 4;
                        flag = true;
                    } else
                    if(k2 < i - 3 && !flag)
                    {
                        hJk = hJk + " (" + code.charAt(k2) + code.charAt(k2 + 1) + code.charAt(k2 + 2) + code.charAt(k2 + 3) + ") ";
                        k2 += 4;
                    }
                } else
                if(code.charAt(k2 - 1) < ' ')
                    hJk = hJk + " ";
                else
                if(code.charAt(k2 - 1) > '\037' && code.charAt(k2 - 1) < '\200')
                    hJk = hJk + code.charAt(k2 - 1);
            }

        }
        if(Code128Set == 'B')
        {
            as = r4t;
            j = 104;
        }
        if(Code128Set == 'C')
        {
            as = t5y;
            j = 105;
            if(s.length() % 2 == 1)
            {
                s = "0" + s;
                hJk = s;
            }
        }
        if(Code128Set == 'B')
            nbvc(g, "bwbwbw", "211214");
        if(Code128Set == 'C')
            nbvc(g, "bwbwbw", "211232");
        if(Code128Set == 'A')
            nbvc(g, "bwbwbw", "211412");
        int l = 1;
        for(int j1 = 0; j1 < s.length(); j1++)
        {
            String s1 = "" + s.charAt(j1);
            if(Code128Set == 'C')
            {
                String s2 = "" + s1;
                if(++j1 < s.length())
                    s2 = s2 + s.charAt(j1);
                int l2 = asdf(t5y, s2);
                if(l2 >= 0)
                {
                    nbvc(g, "bwbwbw", e3r[l2]);
                    j += l2 * l;
                }
            } else
            {
                int i2 = asdf(as, s1);
                if(i2 >= 0)
                {
                    nbvc(g, "bwbwbw", e3r[i2]);
                    j += i2 * l;
                }
            }
            l++;
        }

        if(checkCharacter)
        {
            int k1 = (int)bnmq(j, 103D);
            nbvc(g, "bwbwbw", e3r[k1]);
        }
        nbvc(g, "bwbwbwb", "2331112");
    }

    protected void dfgh(Graphics g)
    {
        String s = code;
        hJk = code;
        String as[] = t5y;
        Code128Set = 'C';
        int i = 105;
        if(s.length() % 2 == 1)
        {
            s = "0" + s;
            hJk = s;
        }
        if(s.charAt(0) != '\312' && s.charAt(1) != '\312')
            s = "\312\312" + s;
        int j = s.length();
        hJk = "";
        for(int k = 0; k < j; k++)
            if(k < j - 3 && s.charAt(k) == '\312' && s.charAt(k + 1) == '\312')
            {
                int l = (s.charAt(k + 2) - 48) * 10 + (s.charAt(k + 3) - 48);
                if(k < j - 5 && (l <= 81 && l >= 80 || l <= 34 && l >= 31))
                {
                    hJk = hJk + " (" + s.charAt(k + 2) + s.charAt(k + 3) + s.charAt(k + 4) + s.charAt(k + 5) + ") ";
                    k += 5;
                } else
                if(k < j - 4 && (l <= 49 && l >= 40 || l <= 25 && l >= 23))
                {
                    hJk = hJk + " (" + s.charAt(k + 2) + s.charAt(k + 3) + s.charAt(k + 4) + ") ";
                    k += 4;
                } else
                if(l <= 30 && l >= 0 || l <= 99 && l >= 90)
                {
                    hJk = hJk + " (" + s.charAt(k + 2) + s.charAt(k + 3) + ") ";
                    k += 3;
                }
            } else
            {
                hJk = hJk + s.charAt(k);
            }

        nbvc(g, "bwbwbw", "211232");
        int i1 = 1;
        for(int j1 = 0; j1 < s.length(); j1++)
        {
            String s1 = "" + s.charAt(j1);
            if(Code128Set == 'C')
            {
                String s2 = "" + s1;
                if(++j1 < s.length())
                    s2 = s2 + s.charAt(j1);
                int i2 = asdf(t5y, s2);
                if(i2 >= 0)
                {
                    nbvc(g, "bwbwbw", e3r[i2]);
                    i += i2 * i1;
                }
            } else
            {
                int l1 = asdf(as, s1);
                if(l1 >= 0)
                {
                    nbvc(g, "bwbwbw", e3r[l1]);
                    i += l1 * i1;
                }
            }
            i1++;
        }

        if(checkCharacter)
        {
            int k1 = (int)bnmq(i, 103D);
            nbvc(g, "bwbwbw", e3r[k1]);
        }
        nbvc(g, "bwbwbwb", "2331112");
    }

    protected void fghj(Graphics g)
    {
        if(code.length() < 7)
            return;
        if(code.length() == 7 && checkCharacter)
            code = code + iopa(code);
        vcxz(g, "bwb", "nnn", 0);
        yUi = zLk;
        for(int i = 0; i < 8; i++)
        {
            String s = "" + code.charAt(i);
            if(i <= 3)
            {
                int j = rtyu(eWq, s);
                nbvc(g, "wbwb", eWq[j][1]);
            } else
            {
                int k = rtyu(qMn, s);
                nbvc(g, "bwbw", qMn[k][1]);
            }
            if(i == 3)
            {
                uIo = zLk;
                vcxz(g, "wbwbw", "nnnnn", 0);
                iOp = zLk;
            }
        }

        oPa = zLk;
        vcxz(g, "bwb", "nnn", 0);
        pAs = zLk;
        if(mQw)
            hjkl(g, fGh);
        else
        if(mNb)
            jklz(g, fGh);
    }

    protected void ghjk(Graphics g)
    {
        int i = 0;
        String s = "";
        if(code.length() == 13)
        {
            fGh = code.substring(11, 13);
            mQw = true;
        }
        if(code.length() == 14)
        {
            fGh = code.substring(12, 14);
            mQw = true;
        }
        if(code.length() == 16)
        {
            fGh = code.substring(11, 16);
            mNb = true;
        }
        if(code.length() == 17)
        {
            fGh = code.substring(12, 17);
            mNb = true;
        }
        if(code.length() < 11)
            return;
        code = code.substring(0, 11);
        code = code + iopa(code);
        i = (new Integer("" + code.charAt(11))).intValue();
        if(code.substring(3, 6).compareTo("000") == 0 || code.substring(3, 6).compareTo("100") == 0 || code.substring(3, 6).compareTo("200") == 0)
            s = code.substring(1, 3) + code.substring(8, 11) + code.charAt(3);
        if(code.substring(3, 6).compareTo("300") == 0 || code.substring(3, 6).compareTo("400") == 0 || code.substring(3, 6).compareTo("500") == 0 || code.substring(3, 6).compareTo("600") == 0 || code.substring(3, 6).compareTo("700") == 0 || code.substring(3, 6).compareTo("800") == 0 || code.substring(3, 6).compareTo("900") == 0)
            s = code.substring(1, 4) + code.substring(9, 11) + "3";
        if(code.substring(4, 6).compareTo("10") == 0 || code.substring(4, 6).compareTo("20") == 0 || code.substring(4, 6).compareTo("30") == 0 || code.substring(4, 6).compareTo("40") == 0 || code.substring(4, 6).compareTo("50") == 0 || code.substring(4, 6).compareTo("60") == 0 || code.substring(4, 6).compareTo("70") == 0 || code.substring(4, 6).compareTo("80") == 0 || code.substring(4, 6).compareTo("90") == 0)
            s = code.substring(1, 5) + code.substring(10, 11) + "4";
        if(code.substring(5, 6).compareTo("0") != 0)
            s = code.substring(1, 6) + code.substring(10, 11);
        hJk = "0" + s + i;
        vcxz(g, "bwb", "nnn", 0);
        yUi = zLk;
        String s1 = tRe[i];
        if(UPCESytem == '1')
            s1 = rEw[i];
        for(int j = 0; j < s.length(); j++)
        {
            String s2 = "" + s.charAt(j);
            int k = -1;
            String as[][] = pOi;
            if(s1.charAt(j) == 'E')
                as = oIu;
            k = rtyu(as, s2);
            nbvc(g, "wbwb", as[k][1]);
        }

        oPa = zLk;
        vcxz(g, "wbwbwb", "nnnnnn", 0);
        pAs = zLk;
        if(mQw)
            hjkl(g, fGh);
        else
        if(mNb)
            jklz(g, fGh);
    }

    protected void hjkl(Graphics g, String s)
    {
        if(fGh.length() > 0)
            s = fGh;
        dFg = (int)((double)cVb * (1.0D - xZl));
        qWe = s;
        if(s.length() != 2)
            return;
        zLk = (int)((double)zLk + (double)xCv * cXz);
        aSd = zLk;
        int i;
        try
        {
            i = Integer.valueOf(s).intValue();
        }
        catch(Exception exception)
        {
            i = 0;
        }
        String s1 = "OO";
        if(bnmq(i, 4D) == 1.0D)
            s1 = "OE";
        if(bnmq(i, 4D) == 2D)
            s1 = "EO";
        if(bnmq(i, 4D) == 3D)
            s1 = "EE";
        vcxz(g, "bwb", "112", dFg);
        String as[][] = pOi;
        if(s1.charAt(0) == 'E')
            as = oIu;
        int j = rtyu(as, "" + s.charAt(0));
        vcxz(g, "wbwb", as[j][1], dFg);
        vcxz(g, "wb", "11", dFg);
        as = pOi;
        if(s1.charAt(1) == 'E')
            as = oIu;
        j = rtyu(as, "" + s.charAt(1));
        vcxz(g, "wbwb", as[j][1], dFg);
        sDf = zLk;
    }

    protected void jklz(Graphics g, String s)
    {
        if(fGh.length() > 0)
            s = fGh;
        dFg = (int)((double)cVb * (1.0D - xZl));
        qWe = s;
        if(s.length() != 5)
            return;
        boolean flag = true;
        int i = 0;
        int j = 0;
        for(int k = s.length() - 1; k >= 0; k--)
        {
            if(flag)
                i += (new Integer("" + s.charAt(k))).intValue();
            else
                j += (new Integer("" + s.charAt(k))).intValue();
            flag = !flag;
        }

        j = i * 3 + j * 9;
        String s1 = "" + j;
        int l = (new Integer("" + s1.charAt(s1.length() - 1))).intValue();
        String s2 = w2e[l];
        zLk = (int)((double)zLk + (double)xCv * cXz);
        aSd = zLk;
        vcxz(g, "bwb", "112", dFg);
        for(int i1 = 0; i1 < 5; i1++)
        {
            String as[][] = pOi;
            if(s2.charAt(i1) == 'E')
                as = oIu;
            int j1 = rtyu(as, "" + s.charAt(i1));
            vcxz(g, "wbwb", as[j1][1], dFg);
            if(i1 < 4)
                vcxz(g, "wb", "11", dFg);
        }

        sDf = zLk;
    }

    protected void klzx(Graphics g)
    {
        String s = code;
        nbvc(g, "bwbwbw", "wnnnnn");
        for(int i = 0; i < s.length(); i++)
        {
            String s1 = "" + code.charAt(i);
            int j = rtyu(jHg, s1);
            if(j >= 0)
                nbvc(g, "bwbwbw", jHg[j][1] + "n");
        }

        nbvc(g, "bwbwbw", "wnnnnn");
    }

    protected void lzxc(Graphics g)
    {
        int i = 0;
        code = code.toUpperCase();
        nbvc(g, "bwbwbwbwb", kJh[rtyu(kJh, "*")][1]);
        zLk = zLk + jKl;
        for(int j = 0; j < code.length(); j++)
        {
            String s = "" + code.charAt(j);
            int l = rtyu(kJh, s);
            if(l > -1)
            {
                i += l;
                nbvc(g, "bwbwbwbwb", kJh[l][1]);
                zLk = zLk + jKl;
            }
        }

        if(checkCharacter)
        {
            int k = (int)bnmq(i, 43D);
            nbvc(g, "bwbwbwbwb", kJh[k][1]);
            zLk = zLk + jKl;
            if(checkCharacterInText)
                hJk = code + "" + kJh[k][0];
            else
                hJk = code;
        }
        nbvc(g, "bwbwbwbwb", kJh[rtyu(kJh, "*")][1]);
    }

    protected void zxcv(Graphics g)
    {
        int i = 0;
        nbvc(g, "bwbwbw", "nnwwnn");
        int j = 1;
        i = 0;
        for(int k = code.length() - 1; k >= 0; k--)
        {
            i += rtyu(gFd, "" + code.charAt(k)) * j;
            if(++j == 11)
                j = 1;
        }

        int l = (int)bnmq(i, 11D);
        j = 2;
        i = l;
        for(int i1 = code.length() - 1; i1 >= 0; i1--)
        {
            i += rtyu(gFd, "" + code.charAt(i1)) * j;
            if(++j == 10)
                j = 1;
        }

        int j1 = (int)bnmq(i, 11D);
        for(int k1 = 0; k1 < code.length(); k1++)
        {
            String s = "" + code.charAt(k1);
            int l1 = rtyu(gFd, s);
            if(l1 > -1)
                nbvc(g, "bwbwbw", gFd[l1][1] + "n");
        }

        if(checkCharacter)
        {
            nbvc(g, "bwbwbw", gFd[l][1] + "n");
            if(checkCharacterInText)
                hJk = code + gFd[l][0];
            else
                hJk = code;
            if(code.length() > 10)
            {
                nbvc(g, "bwbwbw", gFd[j1][1] + "n");
                if(checkCharacterInText)
                    hJk = hJk + gFd[j1][0];
                else
                    hJk = code;
            }
        }
        nbvc(g, "bwbwbw", "nnwwnn");
    }

    protected void xcvb(Graphics g)
    {
        int i = 0;
        nbvc(g, "bwbwbwbw", fDs[rtyu(fDs, "" + bNm)][1] + "n");
        i = rtyu(fDs, "" + bNm) + rtyu(fDs, "" + nMq);
        for(int j = code.length() - 1; j >= 0; j--)
            i += rtyu(fDs, "" + code.charAt(j));

        int k = (int)bnmq(i, 16D);
        if(k != 0)
            k = 16 - k;
        for(int l = 0; l < code.length(); l++)
        {
            String s = "" + code.charAt(l);
            int i1 = rtyu(fDs, s);
            if(i1 > -1)
                nbvc(g, "bwbwbwbw", fDs[i1][1] + "n");
        }

        if(checkCharacter)
        {
            if(checkCharacterInText)
                hJk = code + fDs[k][0];
            else
                hJk = code;
            nbvc(g, "bwbwbwbw", fDs[k][1] + "n");
        }
        nbvc(g, "bwbwbwb", fDs[rtyu(fDs, "" + nMq)][1]);
    }

    protected void cvbn(Graphics g)
    {
        int i = 0;
        nbvc(g, "bw", "wn");
        i = 0;
        String s = "";
        boolean flag = true;
        for(int j = code.length() - 1; j >= 0; j--)
        {
            if(!flag)
                i += rtyu(hGf, "" + code.charAt(j));
            if(flag)
                s = rtyu(hGf, "" + code.charAt(j)) + s;
            flag = !flag;
        }

        s = "" + (new Long(s)).longValue() * 2L;
        for(int k = s.length() - 1; k >= 0; k--)
            i += rtyu(hGf, "" + s.charAt(k));

        int l = (int)bnmq(i, 10D);
        if(l != 0)
            l = 10 - l;
        for(int i1 = 0; i1 < code.length(); i1++)
        {
            String s1 = "" + code.charAt(i1);
            int j1 = rtyu(hGf, s1);
            if(j1 > -1)
                nbvc(g, "bwbwbwbw", hGf[j1][1]);
        }

        if(checkCharacter)
        {
            nbvc(g, "bwbwbwb", hGf[l][1]);
            if(checkCharacterInText)
                hJk = code + hGf[l][0];
            else
                hJk = code;
        }
        nbvc(g, "wbwb", "nnwn");
    }

    protected void vbnm(Graphics g)
    {
        g.setFont(textFont);
        int i = g.getFontMetrics().getHeight();
        g.setColor(backColor);
        g.fillRect(rTy + 2, tYu + 2, 79, i);
        g.setColor(barColor);
        g.drawString(new String(yTr), rTy + 5, i + 5);
    }

    protected static double bnmq(double d, double d1)
    {
        double d2 = d / d1;
        double d3 = Math.round(d2);
        if(d3 > d2)
            d3--;
        return d - d1 * d3;
    }

    protected void nmqw(Graphics g)
    {
        int i = 0;
        nbvc(g, "bwbwbwbwb", kJh[rtyu(kJh, "*")][1]);
        zLk = zLk + jKl;
        for(int j = 0; j < code.length(); j++)
        {
            byte byte0 = (byte)code.charAt(j);
            if(byte0 <= 128)
            {
                String s = iUy[byte0];
                for(int j1 = 0; j1 < s.length(); j1++)
                {
                    String s1 = "" + s.charAt(j1);
                    int k1 = rtyu(kJh, s1);
                    if(k1 > -1)
                    {
                        i += k1;
                        nbvc(g, "bwbwbwbwb", kJh[k1][1]);
                        zLk = zLk + jKl;
                    }
                }

            }
        }

        hJk = "";
        for(int k = 1; k <= code.length(); k++)
        {
            int l = code.charAt(k - 1);
            if(l < 32 && l >= 0)
            {
                if(l == 13 || l == 9)
                    hJk = hJk + "  ";
            } else
            {
                hJk = hJk + (char)l;
            }
        }

        if(checkCharacter)
        {
            int i1 = (int)bnmq(i, 43D);
            nbvc(g, "bwbwbwbwb", kJh[i1][1]);
            zLk = zLk + jKl;
            if(checkCharacterInText)
                hJk = hJk + "" + kJh[i1][0];
        }
        nbvc(g, "bwbwbwbwb", kJh[rtyu(kJh, "*")][1]);
    }

    protected void mqwe(Graphics g)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        nbvc(g, "bwbwbw", "111141");
        for(int l = 0; l < code.length(); l++)
        {
            String s = "" + code.charAt(l);
            int j1 = rtyu(dSa, s);
            if(j1 > -1)
            {
                i += j1;
                nbvc(g, "bwbwbw", dSa[j1][1]);
            }
        }

        int i1 = 1;
        i = 0;
        for(int k1 = code.length() - 1; k1 >= 0; k1--)
        {
            i += rtyu(dSa, "" + code.charAt(k1)) * i1;
            if(++i1 == 21)
                i1 = 1;
        }

        k = (int)bnmq(i, 47D);
        i1 = 2;
        i = k;
        for(int l1 = code.length() - 1; l1 >= 0; l1--)
        {
            i += rtyu(dSa, "" + code.charAt(l1)) * i1;
            if(++i1 == 16)
                i1 = 1;
        }

        j = (int)bnmq(i, 47D);
        if(checkCharacter)
        {
            nbvc(g, "bwbwbw", dSa[k][1]);
            nbvc(g, "bwbwbw", dSa[j][1]);
            if(checkCharacterInText)
                hJk = code + dSa[k][0].charAt(0) + dSa[j][0].charAt(0);
            else
                hJk = code;
        }
        nbvc(g, "bwbwbwb", "1111411");
    }

    protected void mnbv(Graphics g)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        nbvc(g, "bwbwbw", "111141");
        for(int l = 0; l < code.length(); l++)
        {
            byte byte0 = (byte)code.charAt(l);
            if(byte0 <= 128)
            {
                String s = uYt[byte0];
                String s1;
                if(s.length() == 3)
                {
                    s1 = "" + s.charAt(0) + s.charAt(1);
                    int l1 = rtyu(dSa, s1);
                    nbvc(g, "bwbwbw", dSa[l1][1]);
                    s1 = "" + s.charAt(2);
                } else
                {
                    s1 = "" + s.charAt(0);
                }
                int i2 = rtyu(dSa, s1);
                i += i2;
                nbvc(g, "bwbwbw", dSa[i2][1]);
            }
        }

        int i1 = 1;
        i = 0;
        for(int j1 = code.length() - 1; j1 >= 0; j1--)
        {
            byte byte1 = (byte)code.charAt(j1);
            if(byte1 <= 128)
            {
                String s2 = uYt[byte1];
                if(s2.length() == 3)
                {
                    String s3 = "" + s2.charAt(0) + s2.charAt(1);
                    int j2 = rtyu(dSa, s3);
                    i += j2 * (i1 + 1);
                    s3 = "" + s2.charAt(2);
                    j2 = rtyu(dSa, s3);
                    i += j2 * i1;
                    if(++i1 == 21)
                        i1 = 1;
                    if(++i1 == 21)
                        i1 = 1;
                } else
                {
                    String s4 = "" + s2.charAt(0);
                    int k2 = rtyu(dSa, s4);
                    i += k2 * i1;
                    if(++i1 == 21)
                        i1 = 1;
                }
            }
        }

        k = (int)bnmq(i, 47D);
        i1 = 2;
        i = k;
        for(int k1 = code.length() - 1; k1 >= 0; k1--)
        {
            byte byte2 = (byte)code.charAt(k1);
            if(byte2 <= 128)
            {
                String s5 = uYt[byte2];
                if(s5.length() == 3)
                {
                    String s6 = "" + s5.charAt(0) + s5.charAt(1);
                    int l2 = rtyu(dSa, s6);
                    i += l2 * (i1 + 1);
                    s6 = "" + s5.charAt(2);
                    l2 = rtyu(dSa, s6);
                    i += l2 * i1;
                    if(++i1 == 16)
                        i1 = 1;
                    if(++i1 == 16)
                        i1 = 1;
                } else
                {
                    String s7 = "" + s5.charAt(0);
                    int i3 = rtyu(dSa, s7);
                    i += i3 * i1;
                    if(++i1 == 16)
                        i1 = 1;
                }
            }
        }

        j = (int)bnmq(i, 47D);
        if(checkCharacter)
        {
            nbvc(g, "bwbwbw", dSa[k][1]);
            nbvc(g, "bwbwbw", dSa[j][1]);
            if(checkCharacterInText)
                hJk = code + dSa[k][0].charAt(0) + dSa[j][0].charAt(0);
            else
                hJk = code;
        }
        nbvc(g, "bwbwbwb", "1111411");
    }

    protected void nbvc(Graphics g, String s, String s1)
    {
        bvcx(g, s, s1, 0);
    }

    protected void bvcx(Graphics g, String s, String s1, int i)
    {
        for(int j = 0; j < s.length(); j++)
        {
            char c = s.charAt(j);
            char c1 = s1.charAt(j);
            if(c1 == 'n')
                qwer(g, jKl, c == 'b', i);
            if(c1 == 'w')
                qwer(g, kLz, c == 'b', i);
            if(c1 == '1')
                qwer(g, jKl, c == 'b', i);
            if(c1 == '2')
                qwer(g, jKl * 2, c == 'b', i);
            if(c1 == '3')
                qwer(g, jKl * 3, c == 'b', i);
            if(c1 == '4')
                qwer(g, jKl * 4, c == 'b', i);
        }

    }

    protected void vcxz(Graphics g, String s, String s1, int i)
    {
        if(textFont != null && gHj)
        {
            g.setFont(textFont);
            vBn = g.getFontMetrics().getHeight();
        }
        bvcx(g, s, s1, i);
        vBn = 0;
    }

    protected void cxzl()
    {
        int i = code.length();
        lZx = X;
        zXc = X * N;
        if(barType == 2)
        {
            if(bnmq(i, 2D) == 0.0D && checkCharacter)
                i++;
            if(bnmq(i, 2D) == 1.0D && !checkCharacter)
                i++;
            if(checkCharacter)
                i++;
            vCx = (double)(i / 2) * (3D + 2D * N) * X + 7D * X;
        }
        if(barType == 6)
            vCx = (double)(i * 7) * X + 11D * X;
        if(barType == 10)
            vCx = (double)(i * 7) * X + 11D * X;
        if(barType == 11)
            vCx = (double)(i * 7) * X + 11D * X;
        if(barType == 13)
        {
            if(checkCharacter)
                i++;
            if(Code128Set == 'C')
                vCx = (double)(11 * i + 35) * X;
            else
                vCx = (5.5D * (double)i + 35D) * X;
        }
        if(barType == 12)
            vCx = 56D * X + 11D * X;
        if(barType == 7)
        {
            if(checkCharacter)
                i++;
            vCx = (double)i * (3D + 2D * N) * X + 7D * X;
        }
        if(barType == 8)
        {
            if(checkCharacter)
                i++;
            vCx = (double)i * (3D + 2D * N) * X + 7D * X;
        }
        if(barType == 5)
        {
            if(checkCharacter)
                i++;
            vCx = (double)i * (4D + 4D * N) * X + (1.0D + N) * X + (2D + N) * X;
        }
        if(barType == 4)
        {
            if(checkCharacter)
                i++;
            vCx = (double)(i + 2) * (4D + 3D * N) * X;
        }
        if(barType == 3)
        {
            if(checkCharacter || code.length() > 10)
                i++;
            vCx = (double)(i + 2 + 1) * (3D + 2D * N) * X;
        }
        if(barType == 15)
        {
            if(checkCharacter)
                i++;
            vCx = X * 10D;
        }
        if(barType == 0)
        {
            if(checkCharacter)
                i++;
            vCx = (double)(i + 2) * (3D * N + 6D) * X + (double)(i + 1) * nBv * X;
        }
        if(barType == 1)
        {
            int j = 0;
            if(checkCharacter)
                j++;
            for(int l = 0; l < code.length(); l++)
            {
                byte byte0 = (byte)code.charAt(l);
                if(byte0 <= 128)
                {
                    String s = iUy[byte0];
                    j += s.length();
                }
            }

            vCx = (double)(j + 2) * (3D * N + 6D) * X + (double)(j + 1) * nBv * X;
        }
        if(barType == 9 || barType == 14)
        {
            int k = 0;
            if(checkCharacter)
                k++;
            for(int i1 = 0; i1 < code.length(); i1++)
            {
                byte byte1 = (byte)code.charAt(i1);
                if(byte1 <= 128)
                {
                    String s1 = iUy[byte1];
                    if(s1.length() == 1)
                        k++;
                    else
                        k += 2;
                }
            }

            vCx = (double)(k + 2) * (9D * X) + (double)(k + 1) * nBv * X;
        }
        if(barHeightCM == 0.0D)
        {
            barHeightCM = vCx * bVc;
            if(barHeightCM < 0.625D)
                barHeightCM = 0.625D;
        }
        if(barHeightCM != 0.0D)
            cVb = (int)(barHeightCM * (double)xCv);
        if(lZx != 0.0D)
            jKl = (int)(lZx * (double)xCv);
        if(zXc != 0.0D)
            kLz = (int)((double)jKl * N);
        if(jKl <= 0)
            jKl = 1;
        if(kLz <= 1)
            kLz = 2;
    }

    public void paint(Graphics g)
    {
        Graphics g1 = g;
        Image image = null;
        if(rotate != 0)
        {
            String s = System.getProperty("java.version");
            if(s.indexOf("1.0") == 0 || s.indexOf("1.1") == 0)
            {
                image = createImage(getSize().width, getSize().height);
                g1 = image.getGraphics();
            } else
            {
                ImgCreator imgcreator = new ImgCreator();
                image = imgcreator.getImage(getSize().width, getSize().height);
                g1 = imgcreator.getGraphics();
            }
        }
        g.setColor(backColor);
        g.fillRect(0, 0, getSize().width, getSize().height);
        xzlk(g1);
        if(rotate != 0)
        {
            int i = zLk + rTy;
            int j = lKj + tYu;
            Image image1 = zlkj(image, rotate, i, j);
            if(image1 == null)
                g.drawImage(image, 0, 0, null);
            else
                g.drawImage(image1, 0, 0, null);
        }
    }

    protected void xzlk(Graphics g)
    {
        hJk = "";
        cxzl();
        tYu = (int)(topMarginCM * (double)xCv);
        rTy = (int)(leftMarginCM * (double)xCv);
        zLk = rTy;
        g.setColor(backColor);
        int i = getSize().width;
        int j = getSize().height;
        int k = i;
        if(j > k)
            k = j;
        g.fillRect(0, 0, k, k);
        pAs = 0;
        if(barType == 3)
            zxcv(g);
        if(barType == 5)
            cvbn(g);
        if(barType == 4)
            xcvb(g);
        if(barType == 0)
            lzxc(g);
        if(barType == 1)
            nmqw(g);
        if(barType == 2)
            yuio(g);
        if(barType == 9)
            mqwe(g);
        if(barType == 11)
            fghj(g);
        if(barType == 10)
            pasd(g);
        if(barType == 6)
            opas(g);
        if(barType == 12)
            ghjk(g);
        if(barType == 13)
            sdfg(g);
        if(barType == 14)
            mnbv(g);
        if(barType == 7)
            uiop(g);
        if(barType == 8)
            klzx(g);
        if(barType == 15)
            erty(g);
        if(barType == 16)
            tyui(g);
        if(barType == 17)
            dfgh(g);
        if(pAs == 0)
            pAs = zLk;
        if(hJk.length() == 0)
            hJk = code;
        //vbnm(g);//this is a advise method,commented by stephen in 2005-11-14.
        if(showText && textFont != null)
        {
            g.setFont(textFont);
            int l = g.getFontMetrics().getHeight();
            if(rotate == 0 || rotate == 180)
            {
                height = cVb + l + tYu * 2;
                width = zLk + rTy + 2;
                if(barType == 15 || barType == 16)
                    height = (int)(wEr * (double)xCv) + l + 11 + tYu;
            } else
            {
                width = cVb + l + tYu * 2;
                height = zLk + rTy + 2;
                if(barType == 15 || barType == 16)
                    width = (int)(wEr * (double)xCv) + l + 11 + tYu;
            }
        } else
        if(rotate == 0 || rotate == 180)
        {
            height = cVb + tYu * 2;
            width = zLk + rTy + 2;
            if(barType == 15 || barType == 16)
                height = (int)(wEr * (double)xCv) + 1 + tYu;
        } else
        {
            width = cVb + tYu * 2;
            height = zLk + rTy + 2;
            if(barType == 15 || barType == 16)
                width = (int)(wEr * (double)xCv) + 1 + tYu;
        }
        if(autoSize)
            setSize(width, height);
        lKj = cVb + tYu;
        if(showText && textFont != null)
        {
            g.setColor(fontColor);
            g.setFont(textFont);
            int i1 = g.getFontMetrics().getHeight();
            int j1 = g.getFontMetrics().stringWidth("X");
            if((mQw || mNb) && (barType == 11 || barType == 6 || barType == 12 || barType == 10))
            {
                int k1 = (sDf - aSd - g.getFontMetrics().stringWidth(qWe)) / 2;
                if(k1 < 0)
                    k1 = 0;
                g.drawString(qWe, aSd + k1, (tYu + dFg) - 2);
            }
            if(barType == 15 || barType == 16)
            {
                int l1 = (pAs - rTy - g.getFontMetrics().stringWidth(hJk)) / 2;
                if(l1 < 0)
                    l1 = 0;
                g.drawString(hJk, rTy + l1, (int)(wEr * (double)xCv + (double)i1 + 1.0D + (double)tYu));
                lKj = (int)(wEr * (double)xCv) + i1 + 1 + tYu;
                return;
            }
            if(barType == 10 && gHj && hJk.length() >= 13)
            {
                int i2 = 0;
                g.drawString(hJk.substring(0, 1), rTy - j1, cVb + i1 + 1 + tYu);
                i2 = (uIo - yUi - g.getFontMetrics().stringWidth(hJk.substring(1, 7))) / 2;
                if(i2 < 0)
                    i2 = 0;
                g.drawString(hJk.substring(1, 7), yUi + i2, cVb + i1 + 1 + tYu);
                i2 = (oPa - iOp - g.getFontMetrics().stringWidth(hJk.substring(7, 13))) / 2;
                if(i2 < 0)
                    i2 = 0;
                g.drawString(hJk.substring(7, 13), iOp + i2, cVb + i1 + 1 + tYu);
                lKj = cVb + i1 + 1 + tYu;
                return;
            }
            if(barType == 6 && gHj && hJk.length() >= 12)
            {
                int j2 = 0;
                g.drawString(hJk.substring(0, 1), rTy - j1, cVb + i1 + 1 + tYu);
                j2 = (uIo - yUi - g.getFontMetrics().stringWidth(hJk.substring(1, 6))) / 2;
                if(j2 < 0)
                    j2 = 0;
                g.drawString(hJk.substring(1, 6), yUi + j2, cVb + i1 + 1 + tYu);
                j2 = (oPa - iOp - g.getFontMetrics().stringWidth(hJk.substring(6, 11))) / 2;
                if(j2 < 0)
                    j2 = 0;
                g.drawString(hJk.substring(6, 11), iOp + j2, cVb + i1 + 1 + tYu);
                g.drawString(hJk.substring(11, 12), pAs + 3, cVb + i1 + 1 + tYu);
                lKj = cVb + i1 + 1 + tYu;
                return;
            }
            if(barType == 11 && gHj && hJk.length() >= 8)
            {
                int k2 = 0;
                k2 = (uIo - yUi - g.getFontMetrics().stringWidth(hJk.substring(0, 4))) / 2;
                if(k2 < 0)
                    k2 = 0;
                g.drawString(hJk.substring(0, 4), yUi + k2, cVb + i1 + 1 + tYu);
                k2 = (oPa - iOp - g.getFontMetrics().stringWidth(hJk.substring(4, 8))) / 2;
                if(k2 < 0)
                    k2 = 0;
                g.drawString(hJk.substring(4, 8), iOp + k2, cVb + i1 + 1 + tYu);
                lKj = cVb + i1 + 1 + tYu;
                return;
            }
            if(barType == 12 && gHj && hJk.length() >= 8)
            {
                int l2 = 0;
                g.drawString(hJk.substring(0, 1), rTy - j1, cVb + i1 + 1 + tYu);
                l2 = ((oPa + 2) - yUi - g.getFontMetrics().stringWidth(hJk.substring(1, 7))) / 2;
                if(l2 < 0)
                    l2 = 0;
                g.drawString(hJk.substring(1, 7), yUi + l2, cVb + i1 + 1 + tYu);
                g.drawString(hJk.substring(7, 8), pAs + 2, cVb + i1 + 1 + tYu);
                lKj = cVb + i1 + 1 + tYu;
                return;
            }
            int i3 = (pAs - rTy - g.getFontMetrics().stringWidth(hJk)) / 2;
            if(i3 < 0)
                i3 = 0;
            g.drawString(hJk, rTy + i3, cVb + i1 + 1 + tYu);
            lKj = cVb + i1 + 1 + tYu;
        }
    }

    protected Image zlkj(Image image, int i, int j, int k)
    {
        int l = image.getWidth(null);
        int i1 = image.getHeight(null);
        if(j > l)
            j = l;
        if(k > i1)
            k = i1;
        int ai[] = new int[l * i1];
        int ai1[] = new int[j * k];
        PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, l, i1, ai, 0, l);
        try
        {
            pixelgrabber.grabPixels();
        }
        catch(InterruptedException interruptedexception)
        {
            System.err.println("interrupted waiting for pixels!");
            return null;
        }
        if((pixelgrabber.getStatus() & 0x80) != 0)
        {
            System.err.println("image fetch aborted or errored");
            return null;
        }
        if(i == 90)
        {
            for(int j1 = 0; j1 < j; j1++)
            {
                for(int i2 = 0; i2 < k; i2++)
                    ai1[k * (j - (j1 + 1)) + i2] = ai[i2 * l + j1];

            }

            return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(k, j, ai1, 0, k));
        }
        if(i == 180)
        {
            for(int k1 = 0; k1 < j; k1++)
            {
                for(int j2 = 0; j2 < k; j2++)
                    ai1[(k - (j2 + 1)) * j + (j - (k1 + 1))] = ai[j2 * l + k1];

            }

            return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(j, k, ai1, 0, j));
        }
        if(i == 270)
        {
            for(int l1 = 0; l1 < j; l1++)
            {
                for(int k2 = 0; k2 < k; k2++)
                    ai1[k * l1 + (k - (k2 + 1))] = ai[k2 * l + l1];

            }

            return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(k, j, ai1, 0, k));
        } else
        {
            return null;
        }
    }
}
