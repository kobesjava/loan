package com.qtt.jinrong.ui.help;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.util.ToastUtil;

/**
 * Created by yanxin on 16/4/19.
 */
public class UiUtil {

    public static boolean isEmpty(TextView textView, CharSequence showMsg) {
        if (TextUtils.isEmpty(textView.getText())) {
            if (!TextUtils.isEmpty(showMsg)) ToastUtil.showShortToast(showMsg);
            return true;
        }
        return false;
    }

    public static boolean isEmpty(InputEditText inputEditText, CharSequence showMsg) {
        if (TextUtils.isEmpty(inputEditText.getString())) {
            if (!TextUtils.isEmpty(showMsg)) ToastUtil.showShortToast(showMsg);
            return true;
        }
        return false;
    }

    public static boolean isValidInt(InputEditText inputEditText,CharSequence showMsg) {
        Integer val = 0;
        if(!TextUtils.isEmpty(inputEditText.getString())) {
            val = Integer.valueOf(inputEditText.getString());
        }
        if(val.intValue() == 0) {
            if (!TextUtils.isEmpty(showMsg)) ToastUtil.showShortToast(showMsg);
            return false;
        }
        return true;
    }

    public static Integer getIntVal(InputEditText inputEditText,CharSequence showMsg) {
        Integer val = 0;
        if(!TextUtils.isEmpty(inputEditText.getString())) {
            val = Integer.valueOf(inputEditText.getString());
        }
        if(val.intValue() == 0) {
            if (!TextUtils.isEmpty(showMsg)) ToastUtil.showShortToast(showMsg);
        }
        return val;
    }

}