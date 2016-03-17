package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 已还款月数
 * Created by yanxin on 16/3/4.
 */
public enum MonthsRepayedEnum {

    _3个月(1,"0-3个月 ( 含 )"),
    _6个月(2,"3-6个月 ( 含 )"),
    _1年(3,"6个月-1年 ( 含 )"),
    _3年(4,"1-3年 ( 含 )"),
    _5年(5,"3-5年 ( 含 )"),
    _10年(6,"5-10年 ( 含 )"),
    _10年以上(7,"10年以上");

    private int code;
    private String title;
    MonthsRepayedEnum(int code, String title) {
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
        MonthsRepayedEnum[] enums = MonthsRepayedEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
        }
        return vals;
    }

    public static MonthsRepayedEnum find(Integer code) {
        if(code == null) return null;
        MonthsRepayedEnum[] enums = MonthsRepayedEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
