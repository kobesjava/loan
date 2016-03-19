package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 有无社保公积金
 * Created by yanxin on 16/3/4.
 */
public enum SocialFundEnum {

    无社保无公积金(1),
    有社保有公积金(2),
    有社保无公积金(3),
    无社保有公积金(4);

    private int code;
    SocialFundEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        SocialFundEnum[] enums = SocialFundEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static SocialFundEnum find(Integer code) {
        if(code == null) return null;
        SocialFundEnum[] enums = SocialFundEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
