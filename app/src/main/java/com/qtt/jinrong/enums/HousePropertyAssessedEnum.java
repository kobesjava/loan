package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 房产估值
 * Created by yanxin on 16/3/7.
 */
public enum HousePropertyAssessedEnum {

    _30(1,"0-30万"),
    _50(2,"31-50万"),
    _75(3,"51-75万"),
    _100(4,"76-100万"),
    _200(5,"101-200万"),
    _300(6,"201-300万"),
    _500(7,"301-500万"),
    _1000(8,"501-1000万"),
    _1000以上(9,"1000万以上");

    private int code;
    private String title;

    HousePropertyAssessedEnum(int code, String title) {
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
        HousePropertyAssessedEnum[] enums = HousePropertyAssessedEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static HousePropertyAssessedEnum find(Integer code) {
        if(code == null) return null;
        HousePropertyAssessedEnum[] enums = HousePropertyAssessedEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
