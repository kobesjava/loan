package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 房产备用房
 * Created by yanxin on 16/3/7.
 */
public enum HouseSpareEnum {

    有(1),
    无(2);

    private int code;
    HouseSpareEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        HouseSpareEnum[] enums = HouseSpareEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static HouseSpareEnum find(int code) {
        HouseSpareEnum[] enums = HouseSpareEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }

}
