package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产负债率
 * Created by yanxin on 16/3/4.
 */
public enum DebtRateEnum {

    _30P(1,"0-30%"),
    _50P(2,"31-50%"),
    _70P(1,"51-70%"),
    _70P以上(1,"70%以上");

    private int code;
    private String title;

    DebtRateEnum(int code, String title) {
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
        DebtRateEnum[] enums = DebtRateEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static DebtRateEnum find(int code) {
        DebtRateEnum[] enums = DebtRateEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
