/**
 * 
 */
package com.qtt.framework.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * @author bestar
 *
 */
public class TextViewUtil {
	
	public static void setTextColorSpan(TextView view,String text,String spanStr){
		int changeTextColor;  
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);  
        SpannableStringBuilder builder = new SpannableStringBuilder(text);  
        changeTextColor = text.indexOf(spanStr);  
        if (changeTextColor != -1) {  
            builder.setSpan(redSpan, changeTextColor, changeTextColor + spanStr.length(),  
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
            view.setText(builder);  
        } else  
        	view.setText(text);  
	}
	
	public static SpannableStringBuilder getTextColorSpan(String text,String spanStr){
		int changeTextColor;  
		ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);  
		SpannableStringBuilder builder = new SpannableStringBuilder(text);  
		changeTextColor = text.indexOf(spanStr);  
		if (changeTextColor != -1) {  
			builder.setSpan(redSpan, changeTextColor, changeTextColor + spanStr.length(),  
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
			return builder;
		} else {
			return null;
		}
	}
}
