package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 信用卡总额度
 * Created by yanxin on 16/3/4.
 */
public enum CreditTotalLimitEnum {

    无信用卡(1,"无信用"),
    _3000(2,"0-3000元"),
    _5000(3,"30001-5000元"),
    _10000(4,"5001-10000元"),
    _20000(5,"10001-20000元"),
    _30000(6,"20001-30000元"),
    _50000(7,"30001-50000元"),
    _100000(8,"50001-100000元"),
    _100000以上(9,"100000元以上");

    private int code;
    private String title;

    CreditTotalLimitEnum(int code, String title) {
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
        CreditTotalLimitEnum[] enums = CreditTotalLimitEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static CreditTotalLimitEnum find(int code) {
        CreditTotalLimitEnum[] enums = CreditTotalLimitEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
