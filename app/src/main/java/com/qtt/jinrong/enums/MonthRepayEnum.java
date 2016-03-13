package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 月还款额
 * Created by yanxin on 16/3/7.
 */
public enum MonthRepayEnum {

    _1000元(1,"0-1000元"),
    _2000元(2,"1001-2000元"),
    _3000元(3,"2001-3000元"),
    _5000元(4,"3001-5000元"),
    _8000元(5,"5001-8000元"),
    _10000元(6,"8001-10000元"),
    _15000元(7,"10001-15000元"),
    _15000元以上(8,"15000元以上");

    private int code;
    private String title;

    MonthRepayEnum(int code, String title) {
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
        MonthRepayEnum[] enums = MonthRepayEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static MonthRepayEnum find(int code) {
        MonthRepayEnum[] enums = MonthRepayEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
