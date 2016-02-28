package com.finance.framework.util;

/**
 *@author: DengKai
 */

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digest {

	
	public static final String CHARSET_NAME = "UTF-8";
	private MessageDigest md5;
	private String content;
	private Charset charset = Charset.forName(CHARSET_NAME);
	private MD5Digest(String content) {
		try {
			this.setMd5(MessageDigest.getInstance("MD5"));
			this.setContent(content);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public byte[] getDigest(){
		return this.getMd5().digest(getContent().getBytes(this.getCharset()));
	}


	private MessageDigest getMd5() {
		return this.md5;
	}


	private void setMd5(MessageDigest md5) {
		this.md5 = md5;
	}
	
	public static MD5Digest getInstance(String content,String...charsetName){
		MD5Digest md5 = new MD5Digest(content);
		if(charsetName.length>0){
			md5.setCharset(Charset.forName(charsetName[0]));
		}
		return md5;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * @param content 要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString(){
		byte[] digest = this.getDigest();
		StringBuffer buf = new StringBuffer();
		for(byte temp:digest){
			buf.append(DataUtil.toFullHex(temp));
		}
		return buf.toString();
	}
	
	public boolean equals(Object o){
		return toString().equals(o);
	}

	/**
	 * @return charset
	 */
	public Charset getCharset() {
		return this.charset;
	}

	/**
	 * @param charset 要设置的 charset
	 */
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	
	public static String getMD5Digest(String origin) {
		return getInstance(origin).toString();
//		MessageDigest md = null;
//		try {
//			md = MessageDigest.getInstance("MD5");
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} 
//		md.update(origin.getBytes()); 
//		byte[] digestedBytes=md.digest(); 
//		
//		return bytesToString(digestedBytes);
	}
	
//	  public static String bytesToString(byte[] data) {
//	        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
//	                'e', 'f'};
//	        char[] temp = new char[data.length * 2];
//	        for (int i = 0; i < data.length; i++) {
//	            byte b = data[i];
//	            temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
//	            temp[i * 2 + 1] = hexDigits[b & 0x0f];
//	        }
//	        return new String(temp);
//	    }
}
