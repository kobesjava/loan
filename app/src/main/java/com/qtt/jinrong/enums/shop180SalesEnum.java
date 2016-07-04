package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺近180天销售总金额
 * Created by yanxin on 16/3/12.
 */
public enum shop180SalesEnum {

    _10万(1,"0-10万"),
    _30万(2,"11-30万"),
    _50万(3,"31-50万"),
    _100万(4,"51-100万"),
    _300万(5,"101-300万"),
    _500万(6,"301-500万"),
    _1000万(7,"501-1000元"),
    _1000万以上(8,"1000万以上");

    private int code;
    private String title;

    shop180SalesEnum(int code, String title) {
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
        shop180SalesEnum[] enums = shop180SalesEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static shop180SalesEnum find(Integer code) {
        if(code == null) return null;
        shop180SalesEnum[] enums = shop180SalesEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
