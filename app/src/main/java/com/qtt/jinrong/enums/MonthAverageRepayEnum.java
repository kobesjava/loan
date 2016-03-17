package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 月均还款额
 * Created by yanxin on 16/3/7.
 */
public enum MonthAverageRepayEnum {

    _500元(1,"0-500元"),
    _1000元(2,"501-1000元"),
    _2000元(3,"1001-2000元"),
    _3000元(4,"2001-3000元"),
    _5000元(5,"3001-5000元"),
    _8000元(6,"5001-8000元"),
    _10000元(7,"8001-10000元"),
    _20000元(8,"10001-20000元"),
    _20000元以上(9,"20000元以上");

    private int code;
    private String title;

    MonthAverageRepayEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public static List<String> getValues() {
        List<String> titles = new ArrayList<>(5);
        MonthAverageRepayEnum[] enums = MonthAverageRepayEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static MonthAverageRepayEnum find(Integer code) {
        if(code == null) return null;
        MonthAverageRepayEnum[] enums = MonthAverageRepayEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
