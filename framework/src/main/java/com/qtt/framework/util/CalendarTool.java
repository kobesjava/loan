/**
 * 
 */
package com.qtt.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author lei
 * 
 */
public enum CalendarTool {
	zh_CN(Locale.SIMPLIFIED_CHINESE);

	private Locale locale;
	private String pattern;

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	CalendarTool(Locale locale) {
		setLocale(locale);
		if(locale==Locale.SIMPLIFIED_CHINESE)
			setPattern("yyyy-MM-dd HH:mm");
	}
	
	public String format(Date value){
		SimpleDateFormat formatter = new SimpleDateFormat(getPattern(),locale);//yyyy-MM-dd HH:mm:ss
		return formatter.format(value);
	}

}
