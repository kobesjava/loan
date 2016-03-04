package com.android.finance.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public enum CreditUsedLimitEnum {

    LIMIT_500(1,"0-500元"),
    LIMIT_3000(2,"501-3000元"),
    LIMIT_5000(3,"3001-5000元"),
    LIMIT_10000(4,"5001-10000元"),
    LIMIT_15000(5,"10001-15000元"),
    LIMIT_20000(6,"15001-20000元"),
    LIMIT_30000(7,"20001-30000元"),
    LIMIT_50000(8,"30001-50000元"),
    LIMIT_80000(9,"50001-80000元"),
    LIMIT_100000(10,"80001-100000元"),
    LIMIT_100000以上(11,"100000元以上");

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
}
