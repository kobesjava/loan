package com.qtt.jinrong.ui.help;

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

    public static Integer getIntVal(TextView text) {
        int term = 0;
        try {
            term = Integer.parseInt(text.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return term;
    }

    /**
     * 计算利息
     * @param isCompound 是否是复利
     * @param rateMonth  月利率
     * @param term       贷款期限
     * @param amount     贷款金额
     * @return
     */
    public static int calculateRate(boolean isCompound, float rateMonth,int term,float amount) {
        if(rateMonth <= 0 || term <= 0 || amount <= 0) {
            return 0;
        }

        float totalInterest = 0;
        if(!isCompound) {
            totalInterest = rateMonth*amount*term;
        } else {
            for(int i=0;i<term;i++) {
                totalInterest += rateMonth*(amount+totalInterest);
            }
        }
        return (int)totalInterest;
    }

    /**
     * 获取月利率
     * @param monthRate
     * @return
     */
    public static float getMonthRate(String monthRate) {
        if(TextUtils.isEmpty(monthRate)
                || !monthRate.contains("%")) return 0;

        String rate = monthRate.substring(0,monthRate.indexOf("%"));
        if(rate.contains("～")) {
            rate = rate.substring(0,rate.indexOf("～"));
        } else if(rate.contains("~")) {
            rate = rate.substring(0,rate.indexOf("~"));
        }
        try {
            return Float.parseFloat(rate)/100f;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取月利率
     * @param monthRate
     * @return
     */
    public static String getReferMonthRate(String monthRate) {
        if(TextUtils.isEmpty(monthRate)
                || !monthRate.contains("%")) return "";

        String rate = monthRate.substring(0,monthRate.indexOf("%"));
        if(rate.contains("～")) {
            rate = rate.substring(0,rate.indexOf("～"));
        } else if(rate.contains("~")) {
            rate = rate.substring(0,rate.indexOf("~"));
        }
        return rate+"%";
    }

}