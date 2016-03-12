package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 年开票额
 * Created by yanxin on 16/3/7.
 */
public enum BillingYearEnum {

    _30万(1,"0-30万"),
    _50万(2,"31-50万"),
    _100万(3,"51-100万"),
    _200万(4,"101-200万"),
    _300万(5,"201-300万"),
    _500万(6,"301-500万"),
    _1000万(7,"501-1000万"),
    _2000万(8,"1001-2000万"),
    _2000万以上(9,"2000万以上");

    private int code;
    private String title;

    BillingYearEnum(int code, String title) {
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
        BillingYearEnum[] enums = BillingYearEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static BillingYearEnum find(int code) {
        BillingYearEnum[] enums = BillingYearEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
