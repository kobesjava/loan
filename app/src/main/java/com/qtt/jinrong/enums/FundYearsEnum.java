package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 公积金连续缴纳年限
 * Created by yanxin on 16/3/4.
 */
public enum FundYearsEnum {

    _3个月(1,"0-3个月 ( 含 )"),
    _6个月(2,"3-6个月 ( 含 )"),
    _1年(3,"6个月-1年 ( 含 )"),
    _2年(4,"1-2年 ( 含 )"),
    _3年(5,"2-3年 ( 含 )"),
    _5年(6,"3-5年 ( 含 )"),
    _10年(7,"5-10年 ( 含 )"),
    _10年以上(8,"10年以上");

    private int code;
    private String title;
    FundYearsEnum(int code, String title) {
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
        FundYearsEnum[] enums = FundYearsEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
        }
        return vals;
    }

    public static FundYearsEnum find(Integer code) {
        if(code == null) return null;
        FundYearsEnum[] enums = FundYearsEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
