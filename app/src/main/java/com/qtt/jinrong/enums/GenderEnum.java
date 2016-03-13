package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别
 * Created by yanxin on 16/3/7.
 */
public enum GenderEnum {

    男(1),
    女(2);

    private int code;
    GenderEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        List<String> titles = new ArrayList<>(5);
        GenderEnum[] enums = GenderEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].name());
        }
        return titles;
    }

    public static GenderEnum find(int code) {
        GenderEnum[] enums = GenderEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }

}
