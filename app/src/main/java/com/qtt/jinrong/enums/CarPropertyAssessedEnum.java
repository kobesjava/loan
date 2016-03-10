package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 车产估值
 * Created by yanxin on 16/3/4.
 */
public enum CarPropertyAssessedEnum {

    ASSESSED_5万(1,"0-5万"),
    ASSESSED_10万(2,"6-10万"),
    ASSESSED_15万(3,"11-15万"),
    ASSESSED_20万(4,"16-20万"),
    ASSESSED_30万(5,"21-30万"),
    ASSESSED_50万(6,"31-50万"),
    ASSESSED_100万(7,"51-100万"),
    ASSESSED_100万以上(8,"100万以上");

    private int code;
    private String title;
    CarPropertyAssessedEnum(int code, String title) {
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
        CarPropertyAssessedEnum[] enums = CarPropertyAssessedEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
        }
        return vals;
    }

    public static CarPropertyAssessedEnum find(int code) {
        CarPropertyAssessedEnum[] enums = CarPropertyAssessedEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
