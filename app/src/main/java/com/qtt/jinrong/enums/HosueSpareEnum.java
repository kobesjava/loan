package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/7.
 */
public enum HosueSpareEnum {

    有(1),
    无(2);

    private int code;
    HosueSpareEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        HosueSpareEnum[] enums = HosueSpareEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }
}
