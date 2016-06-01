package com.java.util.hdutil;

/**
 * 
 * unicode工具类
 * 
 * @author  王存哲
 * @version  [版本号, 2013-3-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UnicodeUtil {
    /*  public static void main(String[] args) {
            String s = "简介";
            String tt = gbEncoding(s);
            System.out.println(decodeUnicode(tt));
        }*/

    /**
     * 编码
     * @param gbString
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /**
     * 解码
     * @param dataStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
