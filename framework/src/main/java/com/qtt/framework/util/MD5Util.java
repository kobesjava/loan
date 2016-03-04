package com.qtt.framework.util;

/**
 *@author: BruceXie
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class MD5Util {
	public static final String CHARSET_NAME = "UTF-8";
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    protected static MessageDigest messagedigest = null;


    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     *
     * @param md5PwdStr 已知的md5校验码
     * @param password 要校验的字符串
     *
     * @return
     */
    public static boolean checkPassword(String md5PwdStr,String password) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }


    /**
     * 判断从文件中读取的字符串的md5校验码是否与一个已知的md5码相匹配
     *
     * @param md5PwdStr 已知的md5校验码
     * @param p_stream 待校验字符串的文件流
     * @return
     */
    public static boolean checkmd5tofile(String md5PwdStr,InputStream p_stream) {
        String s = getFileMD5String(p_stream);
        return s.equals(md5PwdStr);
    }


    /**
     * 生成字符串的md5校验值
     *
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * 生成字符串的md5校验值
     *
     * @param s
     * @return
     */
    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static   String bufferToHex( byte  bytes[],  int  m,  int  n) {
        StringBuffer stringbuffer = new  StringBuffer( 2  * n);
        int  k = m + n;
        for  ( int  l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return  stringbuffer.toString();
    }

    private  static void  appendHexPair( byte  bt, StringBuffer stringbuffer) {
        char  c0 = hexDigits[(bt &  0xf0 ) >>  4 ]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char  c1 = hexDigits[bt &  0xf ]; // 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * 生成文件的md5校验值
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public   static String getFileMD5String(InputStream p_stream) {
        //InputStream fis;
        try {
            messagedigest=MessageDigest.getInstance("MD5");
            //fis = new FileInputStream(filename);
            byte[] buffer = new byte[2048];
            int numRead = 0;
            while ((numRead = p_stream.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
            p_stream.close();
            return bufferToHex(messagedigest.digest());
        }catch (Exception e) {
            //System.out.println("error");
            return null;
        }
    }

    private final static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[((b[i] & 0xF0) >>> 4)]);
            sb.append(hexChar[(b[i] & 0xF)]);
        }
        return sb.toString();
    }

    /**
     * 获取文件MD5
     * @param file
     * @return
     */
    public static String getMD5(File file) {
        InputStream fis = null;
        String str = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }

            str = toHexString(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {

                }
            }
        }

        return str;
    }

}
