package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 车龄
 * Created by yanxin on 16/3/4.
 */
public enum CarAgeEnum {

    AGE_6个月(1,"0-6个月 ( 含 )"),
    AGE_1年(2,"6个月-1年 ( 含 )"),
    AGE_2年(3,"1-2年 ( 含 )"),
    AGE_3年(4,"2-3年 ( 含 )"),
    AGE_5年(5,"3-5年 ( 含 )"),
    AGE_10年(6,"5-10年 ( 含 )"),
    AGE_10年以上(7,"10年以上");

    private int code;
    private String title;
    CarAgeEnum(int code, String title) {
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
        CarAgeEnum[] enums = CarAgeEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
        }
        return vals;
    }

    public static CarAgeEnum find(int code) {
        CarAgeEnum[] enums = CarAgeEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }

}
