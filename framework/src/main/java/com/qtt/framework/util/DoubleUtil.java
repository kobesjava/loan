package com.qtt.framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by blue on 15/9/14.
 */
public class DoubleUtil {

    /**
     * 把浮点型转换为百分比
     *
     * @param b
     * @return
     */
    public static String doubleToPercentage(Double b) {
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setRoundingMode(RoundingMode.HALF_UP);

        num.setMaximumFractionDigits(2);

        return num.format(b);
    }

    /**
     * 百位四舍五入
     *
     * @param b
     * @return
     */
    public static double hundredsRound(double b) {
        b = b * 100;

        return Math.round(b);
    }

    public static int round(double d) {
        int i = new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return i;
    }

    /**
     * 保留小数点后面两位
     *
     * @param b
     * @return
     */
    public static String DecimalFormat2(double b) {
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(b);
    }
}
