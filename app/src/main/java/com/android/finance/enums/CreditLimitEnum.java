package com.android.finance.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public enum CreditLimitEnum {

    无信用卡(1,"无信用"),
    LIMIT_3000(2,"0-3000元"),
    LIMIT_5000(3,"30001-5000元"),
    LIMIT_10000(4,"5001-10000元"),
    LIMIT_20000(5,"10001-20000元"),
    LIMIT_30000(6,"20001-30000元"),
    LIMIT_50000(7,"30001-50000元"),
    LIMIT_100000(8,"50001-100000元"),
    LIMIT_100000以上(9,"100000元以上");

    private int code;
    private String title;

    CreditLimitEnum(int code, String title) {
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
        CreditLimitEnum[] enums = CreditLimitEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }
}
