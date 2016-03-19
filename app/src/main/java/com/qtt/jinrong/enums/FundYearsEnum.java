package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 公积金连续缴纳年限
 * Created by yanxin on 16/3/4.
 */
public enum FundYearsEnum {

    WORk_3个月(1,"0-3个月 ( 含 )"),
    WORk_6个月(2,"3-6个月 ( 含 )"),
    WORk_1年(3,"6个月-1年 ( 含 )"),
    WORk_2年(4,"1-2年 ( 含 )"),
    WORk_3年(5,"2-3年 ( 含 )"),
    WORk_5年(6,"3-5年 ( 含 )"),
    WORk_10年(7,"5-10年 ( 含 )"),
    WORk_10年以上(8,"10年以上");

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
