package com.jing.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串辅助工具类。
 * @author 朱志杰
 * @version 1.0
 */
public class StringUtil {
	
	private static final int INDEX_NOT_FOUND = -1;
	private static final String EMPTY = "";
	/**
     * <p>The maximum size to which the padding constant(s) can expand.</p>
     */
    private static final int PAD_LIMIT = 8192;
    
    /**
     * 将中间带下划线的字符串转换为像java驼峰式字符串。<br/>
     * 如：good_boy->goodBoy
     * @param sourceStr 源字符串
     * @return String
     */
    public static String underlineToJavaString(String sourceStr){
    	//匹配下划线和后面的一个字符
    	String regexStr = "_.";  
    	Matcher matcher = Pattern.compile(regexStr).matcher(sourceStr);  
    	StringBuffer sb = new StringBuffer();  
    	while (matcher.find()) {  
    	    String g = matcher.group();  
    	    //去掉下划线
    	    g=g.replace("_","");
    	    matcher.appendReplacement(sb, g.toUpperCase());  
    	}  
    	matcher.appendTail(sb);  
    	return sb.toString();
    }
    
    /**
     * 将像java驼峰式字符串转换为中间带下划线的字符串。<br/>
     * 如：goodBoy->good_boy
     * @param sourceStr 源字符串
     * @return String
     */
    public static String javaStringToUnderline(String sourceStr){
    	String regexStr = "[A-Z]";  
    	Matcher matcher = Pattern.compile(regexStr).matcher(sourceStr);  
    	StringBuffer sb = new StringBuffer();  
    	while (matcher.find()) {  
    	    String g = matcher.group();  
    	    matcher.appendReplacement(sb, "_" + g.toLowerCase());  
    	}  
    	matcher.appendTail(sb);  
    	if (sb.charAt(0) == '_') {  
    	    sb.delete(0, 1);  
    	}  
    	return sb.toString();
    }
    
    /**
     * 功能：用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串.<br/>
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     * @author 朱志杰 QQ：862990787
     * 2013-12-12 下午10:14:40
     * @param strSrc 要进行替换操作的字符串
     * @return 替换特殊字符后的字符串
     */
    public static String htmlEncode(String strSrc) {
        if (strSrc == null)
            return "";
        
        //需要进行和转移以后对应的Map
        Map<String,String> coverMap=getHtmlCoverMap();
        for(String key : coverMap.keySet()){
        	strSrc = strSrc.replaceAll(key,coverMap.get(key));
        }
        return strSrc;
    }

    /**
     * 功能：用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串.<br/>
     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
     * @author 朱志杰 QQ：862990787
     * 2013-12-12 下午10:14:40
     * @param strSrc 要进行替换操作的字符串
     * @param quotes 为0时单引号和双引号都替换，为1时不替换单引号，为2时不替换双引号，为3时单引号和双引号都不替换
     * @return 替换特殊字符后的字符串
     */
    public static String htmlEncode(String strSrc, int quotes) {
        if (strSrc == null)
            return "";
        if (quotes == 0) {
            return htmlEncode(strSrc);
        }

        char[] arr_cSrc = strSrc.toCharArray();
        StringBuffer buf = new StringBuffer(arr_cSrc.length);
        char ch;

        for (int i = 0; i < arr_cSrc.length; i++) {
            ch = arr_cSrc[i];
            if (ch == '<')
                buf.append("&lt;");
            else if (ch == '>')
                buf.append("&gt;");
            else if (ch == '"' && quotes == 1)
                buf.append("&quot;");
            else if (ch == '\'' && quotes == 2)
                buf.append("&#039;");
            else if (ch == '&')
                buf.append("&amp;");
            else
                buf.append(ch);
        }

        return buf.toString();
    }

    /**
     * 功能：和htmlEncode正好相反
     * @author 朱志杰 QQ：862990787
     * 2013-12-12 下午10:14:40
     * @param strSrc 要进行转换的字符串
     * @return 转换后的字符串
     */
    public static String htmlDecode(String strSrc) {
        if (strSrc == null)
            return "";
        //需要进行和转移以后对应的Map
        Map<String,String> coverMap=getHtmlCoverMap();
        
        for(String key : coverMap.keySet()){
        	strSrc = strSrc.replaceAll(coverMap.get(key), key);
        }
        return strSrc;
    }
    
    /**
     * 生成html转移的键值对应map
     * @return Map<String,String>
     */
    private static Map<String,String> getHtmlCoverMap(){
    	 Map<String,String> coverMap=new HashMap<String,String>();
         coverMap.put("<", "&lt;");
         coverMap.put(">", "&gt;");
         coverMap.put("\"", "&quot;");
         coverMap.put("'", "&#039;");
         coverMap.put("&", "&amp;");
         return coverMap;
    }
	
	/**
     * 功能：将全角的符号转换成半角符号.(即中文字符转英文字符)
     * @author 朱志杰 QQ：862990787
     * 2013-12-12 下午10:14:40
     * @param str 源字符串
     * @return String
     */
	public static String changeToHalf(String str) {
		String[] decode = {"1","2","3","4","5","6","7","8","9","0","!","@","#","$","%","^","&","*","(",")","a","b","c","d","e","f","g","h","i","j","k","l"
				,"m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U"
				,"V","W","X","Y","Z","-","_","=","+","\\","|","[","]",";",":","'","\"",",","<",".",">","/","?"};
		String source= "１２３４５６７８９０！＠＃＄％︿＆＊（）ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ－＿＝＋＼｜【】；：'\\，〈。〉／？";
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int pos = source.indexOf(str.charAt(i));
			if (pos != -1) {
				result += decode[pos];
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}
    
    /**
     * 功能：将半角的符号转换成全角符号.(即英文字符转中文字符)
     * @author 朱志杰 QQ：862990787
     * 2013-12-12 下午10:14:40
     * @param str 源字符串
     * @return String
     */
	public static String changeToFull(String str) {
		String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
		String[] decode = { "１", "２", "３", "４", "５", "６", "７", "８", "９", "０",
				"！", "＠", "＃", "＄", "％", "︿", "＆", "＊", "（", "）", "ａ", "ｂ",
				"ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ",
				"ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ",
				"Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ",
				"Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ",
				"Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；", "：",
				"'", "\"", "，", "〈", "。", "〉", "／", "？" };
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int pos = source.indexOf(str.charAt(i));
			if (pos != -1) {
				result += decode[pos];
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}
    
    /**
     * 功能：cs串中是否一个都不包含字符数组searchChars中的字符。
     * @author 朱志杰 QQ：862990787
     * 2013-10-10 下午11:29:00
     * @param cs 字符串
     * @param searchChars 字符数组
     * @return boolean 都不包含返回true，否则返回false。
     */
    public static boolean containsNone(CharSequence cs, char... searchChars) {
        if (cs == null || searchChars == null) {
            return true;
        }
        int csLen = cs.length();
        int csLast = csLen - 1;
        int searchLen = searchChars.length;
        int searchLast = searchLen - 1;
        for (int i = 0; i < csLen; i++) {
            char ch = cs.charAt(i);
            for (int j = 0; j < searchLen; j++) {
                if (searchChars[j] == ch) {
                    if (Character.isHighSurrogate(ch)) {
                        if (j == searchLast) {
                            // missing low surrogate, fine, like String.indexOf(String)
                            return false;
                        }
                        if (i < csLast && searchChars[j + 1] == cs.charAt(i + 1)) {
                            return false;
                        }
                    } else {
                        // ch is in the Basic Multilingual Plane
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    //--------------------------------------------------------------------------
    /**
     * <p>编码为Unicode，格式 '\u0020'.</p>
     * 
     * <pre>
     *   CharUtils.unicodeEscaped(' ') = "\u0020"
     *   CharUtils.unicodeEscaped('A') = "\u0041"
     * </pre>
     * 
     * @param ch  源字符串
     * @return 转码后的字符串
     */
    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }
    
    /**
     * <p>转换为字符串，每个元素用separator隔开.</p>
     * <pre>
     * StringUtil.join(null, *)               = null
     * StringUtil.join([], *)                 = ""
     * StringUtil.join([null], *)             = ""
     * StringUtil.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtil.join(["a", "b", "c"], null) = "abc"
     * StringUtil.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  源数组
     * @param separator  分隔符
     * @return String
     */
    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }

        return join(array, separator, 0, array.length);
    }

    /**
     * <p>转换为字符串，每个元素用separator隔开,可以设置起止的序号。</p>
     *
     * <pre>
     * StringUtil.join(null, *)               = null
     * StringUtil.join([], *)                 = ""
     * StringUtil.join([null], *)             = ""
     * StringUtil.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtil.join(["a", "b", "c"], null) = "abc"
     * StringUtil.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  源数组
     * @param separator  分隔符
     * @param startIndex 开始下标
     * @param endIndex 结束下标
     * @return String
     */
    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        
        StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <p>转换为字符串，每个元素用separator隔开.</p>
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = ""
     * StringUtil.join([null], *)              = ""
     * StringUtil.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtil.join(["a", "b", "c"], null)  = "abc"
     * StringUtil.join(["a", "b", "c"], "")    = "abc"
     * StringUtil.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  源数组
     * @param separator  分隔符
     * @return String
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * <p>转换为字符串，每个元素用separator隔开,可以设置起止的序号。</p>
     *
     * <pre>
     * StringUtil.join(null, *)                = null
     * StringUtil.join([], *)                  = ""
     * StringUtil.join([null], *)              = ""
     * StringUtil.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtil.join(["a", "b", "c"], null)  = "abc"
     * StringUtil.join(["a", "b", "c"], "")    = "abc"
     * StringUtil.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  源数组
     * @param separator  分隔符
     * @param startIndex 开始下标
     * @param endIndex 结束下标
     * @return String
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }

        StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <p>转换为字符串，每个元素用separator隔开。</p>
     *
     * @param iterable  源
     * @param separator  分隔符
     * @return String
     */
    public static String join(Iterator<?> iterator, char separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }

        // two or more elements
        StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }

        return buf.toString();
    }

    /**
     * <p>转换为字符串，每个元素用separator隔开。</p>
     *
     * @param iterable  源
     * @param separator  分隔符
     * @return String
     */
    public static String join(Iterator<?> iterator, String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }

        // two or more elements
        StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * <p>转换为字符串，每个元素用separator隔开。</p>
     *
     * @param iterable  源
     * @param separator  分隔符
     * @return String
     */
    public static String join(Iterable<?> iterable, char separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    /**
     * <p>转换为字符串，每个元素用separator隔开。</p>
     *
     * @param iterable  源
     * @param separator  分隔符
     * @return String
     */
    public static String join(Iterable<?> iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }
    
    /**
     * <p>进行tostring操作，如果传入的是null，返回空字符串。</p>
     *
     * <pre>
     * ObjectUtils.toString(null)         = ""
     * ObjectUtils.toString("")           = ""
     * ObjectUtils.toString("bat")        = "bat"
     * ObjectUtils.toString(Boolean.TRUE) = "true"
     * </pre>
     *
     * @param obj  源
     * @return String
     */
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * <p>进行tostring操作，如果传入的是null，返回指定的默认值。</p>
     *
     * <pre>
     * ObjectUtils.toString(null, null)           = null
     * ObjectUtils.toString(null, "null")         = "null"
     * ObjectUtils.toString("", "null")           = ""
     * ObjectUtils.toString("bat", "null")        = "bat"
     * ObjectUtils.toString(Boolean.TRUE, "null") = "true"
     * </pre>
     *
     * @param obj  源
     * @param nullStr  如果obj为null时返回这个指定值
     * @return String
     */
    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }
    
    /**
     * <p>只从源字符串中移除指定开头子字符串.</p>
     * <pre>
     * StringUtil.removeStart(null, *)      = null
     * StringUtil.removeStart("", *)        = ""
     * StringUtil.removeStart(*, null)      = *
     * StringUtil.removeStart("www.domain.com", "www.")   = "domain.com"
     * StringUtil.removeStart("domain.com", "www.")       = "domain.com"
     * StringUtil.removeStart("www.domain.com", "domain") = "www.domain.com"
     * StringUtil.removeStart("abc", "")    = "abc"
     * </pre>
     *
     * @param str  源字符串
     * @param remove  将要被移除的子字符串
     * @return String
     */
    public static String removeStart(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)){
            return str.substring(remove.length());
        }
        return str;
    }
    
    /**
     * <p>只从源字符串中移除指定结尾的子字符串.</p>
     * <pre>
     * StringUtil.removeEnd(null, *)      = null
     * StringUtil.removeEnd("", *)        = ""
     * StringUtil.removeEnd(*, null)      = *
     * StringUtil.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtil.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtil.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtil.removeEnd("abc", "")    = "abc"
     * </pre>
     *
     * @param str  源字符串
     * @param remove  将要被移除的子字符串
     * @return String
     */
    public static String removeEnd(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }
    
    /**
     * <p>将一个字符串重复N次</p>
     *
     * <pre>
     * StringUtil.repeat(null, 2) = null
     * StringUtil.repeat("", 0)   = ""
     * StringUtil.repeat("", 2)   = ""
     * StringUtil.repeat("a", 3)  = "aaa"
     * StringUtil.repeat("ab", 2) = "abab"
     * StringUtil.repeat("a", -2) = ""
     * </pre>
     *
     * @param str  源字符串
     * @param repeat  重复的次数
     * @return String
     */
    public static String repeat(String str, int repeat) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1 :
                return repeat(str.charAt(0), repeat);
            case 2 :
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default :
                StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * <p>将一个字符串重复N次，并且中间加上指定的分隔符 </p>
     *
     * <pre>
     * StringUtil.repeat(null, null, 2) = null
     * StringUtil.repeat(null, "x", 2)  = null
     * StringUtil.repeat("", null, 0)   = ""
     * StringUtil.repeat("", "", 2)     = ""
     * StringUtil.repeat("", "x", 3)    = "xxx"
     * StringUtil.repeat("?", ", ", 3)  = "?, ?, ?"
     * </pre>
     *
     * @param str        源字符串
     * @param separator  分隔符
     * @param repeat     重复次数
     * @return String
     */
    public static String repeat(String str, String separator, int repeat) {
        if(str == null || separator == null) {
            return repeat(str, repeat);
        } else {
            // given that repeat(String, int) is quite optimized, better to rely on it than try and splice this into it
            String result = repeat(str + separator, repeat);
            return removeEnd(result, separator);
        }
    }

    /**
     * <p>将某个字符重复N次.</p>
     *
     * @param ch  某个字符
     * @param repeat  重复次数
     * @return String
     */
    public static String repeat(char ch, int repeat) {
        char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

	
	 /**
     * <p>字符串长度达不到指定长度时，在字符串右边补指定的字符.</p>
     * <pre>
     * StringUtil.rightPad(null, *, *)     = null
     * StringUtil.rightPad("", 3, 'z')     = "zzz"
     * StringUtil.rightPad("bat", 3, 'z')  = "bat"
     * StringUtil.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtil.rightPad("bat", 1, 'z')  = "bat"
     * StringUtil.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  源字符串
     * @param size  指定的长度
     * @param padChar  进行补充的字符
     * @return String
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }

    /**
     * <p>扩大字符串长度，从左边补充指定字符</p>
     * <pre>
     * StringUtil.rightPad(null, *, *)      = null
     * StringUtil.rightPad("", 3, "z")      = "zzz"
     * StringUtil.rightPad("bat", 3, "yz")  = "bat"
     * StringUtil.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtil.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtil.rightPad("bat", 1, "yz")  = "bat"
     * StringUtil.rightPad("bat", -1, "yz") = "bat"
     * StringUtil.rightPad("bat", 5, null)  = "bat  "
     * StringUtil.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str  源字符串
     * @param size  扩大后的长度
     * @param padStr  在右边补充的字符串
     * @return String
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    /**
     * <p>扩大字符串长度，从左边补充空格</p>
     *
     * <pre>
     * StringUtil.leftPad(null, *)   = null
     * StringUtil.leftPad("", 3)     = "   "
     * StringUtil.leftPad("bat", 3)  = "bat"
     * StringUtil.leftPad("bat", 5)  = "  bat"
     * StringUtil.leftPad("bat", 1)  = "bat"
     * StringUtil.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  源字符串
     * @param size   扩大后的长度
     * @return String
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>扩大字符串长度，从左边补充指定的字符</p>
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)     = null
     * StringUtil.leftPad("", 3, 'z')     = "zzz"
     * StringUtil.leftPad("bat", 3, 'z')  = "bat"
     * StringUtil.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtil.leftPad("bat", 1, 'z')  = "bat"
     * StringUtil.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  源字符串
     * @param size  扩大后的长度
     * @param padStr  补充的字符
     * @return String
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    /**
     * <p>扩大字符串长度，从左边补充指定的字符</p>
     * <pre>
     * StringUtil.leftPad(null, *, *)      = null
     * StringUtil.leftPad("", 3, "z")      = "zzz"
     * StringUtil.leftPad("bat", 3, "yz")  = "bat"
     * StringUtil.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtil.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.leftPad("bat", 1, "yz")  = "bat"
     * StringUtil.leftPad("bat", -1, "yz") = "bat"
     * StringUtil.leftPad("bat", 5, null)  = "  bat"
     * StringUtil.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str  源字符串
     * @param size  扩大后的长度
     * @param padStr  补充的字符串
     * @return String
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * <p>扩大字符串长度并将现在的字符串居中，被扩大部分用空格填充。<p>
     * <pre>
     * StringUtil.center(null, *)   = null
     * StringUtil.center("", 4)     = "    "
     * StringUtil.center("ab", -1)  = "ab"
     * StringUtil.center("ab", 4)   = " ab "
     * StringUtil.center("abcd", 2) = "abcd"
     * StringUtil.center("a", 4)    = " a  "
     * </pre>
     *
     * @param str  源字符串
     * @param size  扩大后的长度
     * @return String
     */
    public static String center(String str, int size) {
        return center(str, size, ' ');
    }

    /**
     * <p>将字符串长度修改为指定长度，并进行居中显示。</p>
     *
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, ' ')     = "    "
     * StringUtil.center("ab", -1, ' ')  = "ab"
     * StringUtil.center("ab", 4, ' ')   = " ab"
     * StringUtil.center("abcd", 2, ' ') = "abcd"
     * StringUtil.center("a", 4, ' ')    = " a  "
     * StringUtil.center("a", 4, 'y')    = "yayy"
     * </pre>
     *
     * @param str  源字符串
     * @param size  指定的长度
     * @param padStr 长度不够时补充的字符串
     * @return String
     * @throws IllegalArgumentException 如果被补充字符串为 null或者 empty
     */
    public static String center(String str, int size, char padChar) {
        if (str == null || size <= 0) {
            return str;
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padChar);
        str = rightPad(str, size, padChar);
        return str;
    }

    /**
     * <p>将字符串长度修改为指定长度，并进行居中显示。</p>
     *
     * <pre>
     * StringUtil.center(null, *, *)     = null
     * StringUtil.center("", 4, " ")     = "    "
     * StringUtil.center("ab", -1, " ")  = "ab"
     * StringUtil.center("ab", 4, " ")   = " ab"
     * StringUtil.center("abcd", 2, " ") = "abcd"
     * StringUtil.center("a", 4, " ")    = " a  "
     * StringUtil.center("a", 4, "yz")   = "yayz"
     * StringUtil.center("abc", 7, null) = "  abc  "
     * StringUtil.center("abc", 7, "")   = "  abc  "
     * </pre>
     *
     * @param str  源字符串
     * @param size  指定的长度
     * @param padStr 长度不够时补充的字符串
     * @return String
     * @throws IllegalArgumentException 如果被补充字符串为 null或者 empty
     */
    public static String center(String str, int size, String padStr) {
        if (str == null || size <= 0) {
            return str;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padStr);
        str = rightPad(str, size, padStr);
        return str;
    }

     /**
     * <p>检查字符串是否全部为小写.</p>
     * <pre>
     * StringUtil.isAllLowerCase(null)   = false
     * StringUtil.isAllLowerCase("")     = false
     * StringUtil.isAllLowerCase("  ")   = false
     * StringUtil.isAllLowerCase("abc")  = true
     * StringUtil.isAllLowerCase("abC") = false
     * </pre>
     *
     * @param cs  源字符串
     * @return String
     */
    public static boolean isAllLowerCase(String cs) {
        if (cs == null || isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLowerCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>检查是否都是大写.</p>
     * <pre>
     * StringUtil.isAllUpperCase(null)   = false
     * StringUtil.isAllUpperCase("")     = false
     * StringUtil.isAllUpperCase("  ")   = false
     * StringUtil.isAllUpperCase("ABC")  = true
     * StringUtil.isAllUpperCase("aBC") = false
     * </pre>
     *
     * @param cs 源字符串
     * @return String
     */
    public static boolean isAllUpperCase(String cs) {
        if (cs == null || StringUtil.isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isUpperCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>反转字符串.</p>
     * <pre>
     * StringUtil.reverse(null)  = null
     * StringUtil.reverse("")    = ""
     * StringUtil.reverse("bat") = "tab"
     * </pre>
     *
     * @param str  源字符串
     * @return String
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }
	
	 /**
     * <p>字符串达不到一定长度时在右边补空白.</p>
     * <pre>
     * StringUtil.rightPad(null, *)   = null
     * StringUtil.rightPad("", 3)     = "   "
     * StringUtil.rightPad("bat", 3)  = "bat"
     * StringUtil.rightPad("bat", 5)  = "bat  "
     * StringUtil.rightPad("bat", 1)  = "bat"
     * StringUtil.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  源字符串
     * @param size  指定的长度
     * @return String
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }
	
	/**
     * 从右边截取字符串.</p>
     * <pre>
     * StringUtil.right(null, *)    = null
     * StringUtil.right(*, -ve)     = ""
     * StringUtil.right("", *)      = ""
     * StringUtil.right("abc", 0)   = ""
     * StringUtil.right("abc", 2)   = "bc"
     * StringUtil.right("abc", 4)   = "abc"
     * </pre>
     * @param str  源字符串
     * @param len  长度
     * @return String
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }
	
	 /**
     * <p>截取一个字符串的前几个.</p>
     * <pre>
     * StringUtil.left(null, *)    = null
     * StringUtil.left(*, -ve)     = ""
     * StringUtil.left("", *)      = ""
     * StringUtil.left("abc", 0)   = ""
     * StringUtil.left("abc", 2)   = "ab"
     * StringUtil.left("abc", 4)   = "abc"
     * </pre>
     * @param str  源字符串
     * @param len  截取的长度
     * @return the String
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }
	
    /**
     * <p>得到tag字符串中间的子字符串，只返回第一个匹配项。</p>
     * <pre>
     * StringUtil.substringBetween(null, *)            = null
     * StringUtil.substringBetween("", "")             = ""
     * StringUtil.substringBetween("", "tag")          = null
     * StringUtil.substringBetween("tagabctag", null)  = null
     * StringUtil.substringBetween("tagabctag", "")    = ""
     * StringUtil.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     * @param str  源字符串。
     * @param tag  标识字符串。
     * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
     */
    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>得到两个字符串中间的子字符串，只返回第一个匹配项。</p>
     * <pre>
     * StringUtil.substringBetween("wx[b]yz", "[", "]") = "b"
     * StringUtil.substringBetween(null, *, *)          = null
     * StringUtil.substringBetween(*, null, *)          = null
     * StringUtil.substringBetween(*, *, null)          = null
     * StringUtil.substringBetween("", "", "")          = ""
     * StringUtil.substringBetween("", "", "]")         = null
     * StringUtil.substringBetween("", "[", "]")        = null
     * StringUtil.substringBetween("yabcz", "", "")     = ""
     * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     * @param str  源字符串
     * @param open  起字符串。
     * @param close  末字符串。
     * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
     */
    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            int end = str.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    /**
     * <p>得到两个字符串中间的子字符串，所有匹配项组合为数组并返回。</p>
     * <pre>
     * StringUtil.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
     * StringUtil.substringsBetween(null, *, *)            = null
     * StringUtil.substringsBetween(*, null, *)            = null
     * StringUtil.substringsBetween(*, *, null)            = null
     * StringUtil.substringsBetween("", "[", "]")          = []
     * </pre>
     *
     * @param str  源字符串
     * @param open  起字符串。
     * @param close  末字符串。
     * @return String 子字符串数组, 如果没有符合要求的，返回{@code null}。
     */
    public static String[] substringsBetween(String str, String open, String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        int strLen = str.length();
        if (strLen == 0) {
            return new String[0];
        }
        int closeLen = close.length();
        int openLen = open.length();
        List<String> list = new ArrayList<String>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(new String [list.size()]);
    }
	
	/**
	 * 功能：切换字符串中的所有字母大小写。<br/>
	 * <pre>
     * StringUtil.swapCase(null)                 = null
     * StringUtil.swapCase("")                   = ""
     * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
	 * @author 朱志杰 QQ：695520848
	 * Aug 21, 2013 7:11:37 AM
	 * @param str 源字符串
	 * @return String
	 */
	public static String swapCase(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        char[] buffer = str.toCharArray();

        boolean whitespace = true;

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isLowerCase(ch)) {
                if (whitespace) {
                    buffer[i] = Character.toTitleCase(ch);
                    whitespace = false;
                } else {
                    buffer[i] = Character.toUpperCase(ch);
                }
            } else {
                whitespace = Character.isWhitespace(ch);
            }
        }
        return new String(buffer);
    }

	/**
	 * 功能：截取出最后一个标志位之后的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 11:41:00 AM
	 * @param sourceStr 被截取的字符串
	 * @param expr 分隔符
	 * @return String
	 */
	public static String substringAfterLast(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}
		
		int pos = sourceStr.lastIndexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(pos + expr.length());
	}
	
	/**
	 * 功能：截取出最后一个标志位之前的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 11:41:00 AM
	 * @param sourceStr 被截取的字符串
	 * @param expr 分隔符
	 * @return String
	 */
	public static String substringBeforeLast(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}
		int pos = sourceStr.lastIndexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(0, pos);
	}
	
	/**
	 * 功能：截取出第一个标志位之后的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 11:41:00 AM
	 * @param sourceStr 被截取的字符串
	 * @param expr 分隔符
	 * @return String
	 */
	public static String substringAfter(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}
		
		int pos = sourceStr.indexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(pos + expr.length());
	}
	
	/**
	 * 功能：截取出第一个标志位之前的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中存在不止一个，以第一个位置为准。
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 11:41:00 AM
	 * @param sourceStr 被截取的字符串
	 * @param expr 分隔符
	 * @return String
	 */
	public static String substringBefore(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}
		int pos = sourceStr.indexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(0, pos);
	}
	
	/**
	 * 功能：检查这个字符串是不是空字符串。<br/>
	 * 如果这个字符串为null或者trim后为空字符串则返回true，否则返回false。
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 11:33:28 AM
	 * @param chkStr 被检查的字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String chkStr){
		if(chkStr==null){
			return true;
		}else{
			return "".equals(chkStr.trim()) ? true : false;
		}
	}
	
	/**
	 * 功能：检查这个字符序列(CharSequence)是不是空。<br/>
	 * 如果这个字符序列为null或者trim后为空序列则返回true，否则返回false。
	 * @author 朱志杰 QQ：695520848
	 * Jun 9, 2013 11:33:28 AM
	 * @param chkSeq 被检查的字符序列
	 * @return boolean
	 */
	public static boolean isEmpty(CharSequence chkSeq){
		if(chkSeq==null){
			return true;
		}else{
			return "".equals(chkSeq.toString().trim()) ? true : false;
		}
	}
	
	
	/**
	 * 如果字符串没有超过最长显示长度返回原字符串，否则从开头截取指定长度并加...返回。
	 * @param str 原字符串
	 * @param length 字符串最长显示的长度
	 * @return 转换后的字符串
	 */
	public static String trimString(String str, int length) {
		if (str == null) {
			return "";
		} else if (str.length() > length) {
			return str.substring(0, length - 3) + "...";
		} else {
			return str;
		}
	}
}
