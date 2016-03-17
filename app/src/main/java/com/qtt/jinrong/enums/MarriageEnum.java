package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 婚姻情况
 * Created by yanxin on 16/3/4.
 */
public enum  MarriageEnum {

    已婚(1,"已婚"),
    未婚离异(2,"未婚/离异");

    private int code;
    private String title;

    MarriageEnum(int code,String title) {
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
        MarriageEnum[] enums = MarriageEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static MarriageEnum find(Integer code) {
        if(code == null) return null;
        MarriageEnum[] enums = MarriageEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
