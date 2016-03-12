package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 年营业收入
 * Created by yanxin on 16/3/7.
 */
public enum IncomeYearEnum {

    _50万(1,"0-50万"),
    _100万(2,"51-100万"),
    _200万(3,"101-200万"),
    _300万(4,"201-300万"),
    _500万(5,"301-500万"),
    _1000(6,"501-1000万"),
    _2000(7,"1001-2000万"),
    _2000万以上(8,"2000万以上");

    private int code;
    private String title;

    IncomeYearEnum(int code, String title) {
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
        IncomeYearEnum[] enums = IncomeYearEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static IncomeYearEnum find(int code) {
        IncomeYearEnum[] enums = IncomeYearEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
