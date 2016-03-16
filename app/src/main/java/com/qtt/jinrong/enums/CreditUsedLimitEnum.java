package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 信用卡已使用额度
 * Created by yanxin on 16/3/4.
 */
public enum CreditUsedLimitEnum {

    _500(1,"0-500元"),
    _3000(2,"501-3000元"),
    _5000(3,"3001-5000元"),
    _10000(4,"5001-10000元"),
    _15000(5,"10001-15000元"),
    _20000(6,"15001-20000元"),
    _30000(7,"20001-30000元"),
    _50000(8,"30001-50000元"),
    _80000(9,"50001-80000元"),
    _100000(10,"80001-100000元"),
    _100000以上(11,"100000元以上");

    private int code;
    private String title;

    CreditUsedLimitEnum(int code, String title) {
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
        CreditUsedLimitEnum[] enums = CreditUsedLimitEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static CreditUsedLimitEnum find(Integer code) {
        if(code == null) return null;
        CreditUsedLimitEnum[] enums = CreditUsedLimitEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
