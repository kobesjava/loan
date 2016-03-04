package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public enum IncomMethodEnum {

    银行打卡(1,"银行打卡"),
    现金发放(2,"现金发放"),
    部分打卡部分现金(3,"部分打卡,部分现金");

    private int code;
    private String title;

    IncomMethodEnum(int code, String title) {
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
        IncomMethodEnum[] enums = IncomMethodEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }
}
