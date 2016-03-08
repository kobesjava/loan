package com.qtt.jinrong.ui.widget.text;

import android.text.InputFilter;
import android.text.Spanned;

import com.qtt.framework.util.StringUtil;

public class InputFilterUtil {


    /**
     * 获取最大输出字符串过滤器
     *
     * @param max
     * @return
     */
    public static InputFilter getInputFilterLength(int max) {
        return new InputFilter.LengthFilter(max);

    }


    /**
     * 获取中文输入过滤器
     *
     * @return
     */
    public static InputFilter getInputFilterChese() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (StringUtil.checkNameChese(source.toString())) {
                    return source;
                }
                return "";
            }
        };
    }


    /**
     * 身份证号码过滤器
     *
     * @return
     */
    public static InputFilter getIDcardFilter() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                //判断是否是一次性设置进去的
                if (end - start >= 15) {
                    source = sourceDispose1(source);
                } else {
                    source = InputFilterUtil.sourceDispose(source, dstart);
                }
                //判断 3 3 4 4 4
                return source;
            }


        };
    }

    public static CharSequence sourceDispose1(CharSequence source) {

        StringBuffer stringBuffer = new StringBuffer(source);

        // 1234
        if (source.length() > 3) {
            stringBuffer.insert(3, " ");
        }
        if (source.length() > 6) {
            stringBuffer.insert(7, " ");
        }
        if (source.length() > 10) {
            stringBuffer.insert(12, " ");
        }
        if (source.length() > 14) {
            stringBuffer.insert(17, " ");
        }

        return stringBuffer;
    }


    private static CharSequence sourceDispose(CharSequence source, int dstart) {
        if (source == "") return "";
        if (dstart == 3) {
            if (source != " ")
                source = " " + source;
        } else if (dstart == 7) {
            if (source != " ")
                source = " " + source;
        } else if (dstart == 12) {
            if (source != " ")
                source = " " + source;
        } else if (dstart == 17) {
            if (source != " ")
                source = " " + source;
        } else if (source == " ") {
            source = "";
        }
        //判断 3 3 4 4 4
        return source;
    }

}
