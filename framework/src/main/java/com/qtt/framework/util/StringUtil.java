package com.qtt.framework.util;

import java.text.DecimalFormat;

public class StringUtil {
    /**
     * 字符串为Null或者为""
     *
     * @param str
     * @return
     */
    public static boolean isEmptyOrNull(String str) {
        return str == null || "".equals(str);
    }


    /**
     * 字符串为Null或者为""
     *
     * @param str
     * @return
     */
    public static boolean isEmptyNull(String str) {
        return str == null || "".equals(str) || "0".equals(str);
    }

    /**
     * 字符串为Null或者为""
     *
     * @param str
     * @return
     */
    public static boolean returnEmptyNull(String str) {
        return str == null || str.equals("");
    }


    /**
     * 比较两个字符串是否相等（两个同为NULL不相等，两个同为""相等）
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareString(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        } else {
            return str1.equals(str2);
        }
    }

    /**
     * 无效数据返回-1
     *
     * @param intValue
     * @return
     */
    public static int toInt(String intValue) {
        try {
            return Integer.parseInt(intValue);
        } catch (Exception e) {
            return -1;
        }
    }

    private static DecimalFormat mformat = new DecimalFormat("########.## ");

    /**
     * 格式化输出 "########.## "
     *
     * @param f
     * @return
     */
    public static String fromatString(double f) {
        try {
            return mformat.format(f);
        } catch (Exception e) {
            return "-1";
        }
    }


    /**
     * 判断各种空情况
     */
    public static boolean isEmpty(String str) {

        return trim(str).equals("") || trim(str).equals("null") ? true : false;
    }

    static String trim(String str) {
        if (str == null) {
            return "";
        }
        return str.trim();
    }

    /**
     * 字符串分割
     *
     * @param split
     * @param str
     * @return
     */
    public static String[] splitString(String split, String str) {
        String[] array = str.split(split);
        return array;
    }


    /**
     * 电话号码格式化，去掉+86,去掉空格
     *
     * @param phoneNumber
     * @return
     */
    public static String phoneNumberRegularExpression(String phoneNumber) {

        return phoneNumber.replace("+86", "").replace(" ", "");
    }

    /**
     * 去掉String空格
     *
     * @return
     */
    public static String replaceSpace(String str) {
        return str.replace(" ", "");
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return
     */
    public static boolean checkNameChese(String name) {
        boolean res = true;
//        char[] cTemp = name.toCharArray();
//        for (int i = 0; i < name.length(); i++) {
//            if (!isChinese(cTemp[i])) {
//                res = false;
//                break;
//            }
//        }
//        return res;
        char[] ch = name.toCharArray();

        int varlength = 0;
        for (int i = 0; i < ch.length; i++) {
            // changed by zyf 0825 , bug 6918，加入中文标点范围 ， TODO 标点范围有待具体化
            if ((ch[i] >= 0x2E80 && ch[i] <= 0xFE4F) || (ch[i] >= 0xA13F && ch[i] <= 0xAA40) || ch[i] >= 0x80) { // 中文字符范围0x4e00 0x9fbb
                varlength = varlength + 2;
            } else {
                res = false;
            }
        }
        return res;
    }

}
