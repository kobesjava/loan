package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 月对公流水
 * Created by yanxin on 16/3/7.
 */
public enum MonthPublicWaterEnum {

    _5万(1,"0-5万"),
    _10万(2,"6-10万"),
    _30万(3,"11-30万"),
    _50万(4,"31-50万"),
    _100万(5,"51-100万"),
    _200万(6,"101-200万"),
    _300万(7,"201-300万"),
    _500万(8,"301-500万"),
    _1000万(9,"501-1000万"),
    _1000万以上(10,"1000万以上");

    private int code;
    private String title;

    MonthPublicWaterEnum(int code, String title) {
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
        MonthPublicWaterEnum[] enums = MonthPublicWaterEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static MonthPublicWaterEnum find(int code) {
        MonthPublicWaterEnum[] enums = MonthPublicWaterEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
