package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by yanxin on 16/3/4.
 */
public enum OperatorYearsEnum {

    _6个月(1,"0-6个月 ( 含 )"),
    _1年(2,"6个月-1年 ( 含 )"),
    _2年(3,"1-2年 ( 含 )"),
    _3年(4,"2-3年 ( 含 )"),
    _5年(5,"3-5年 ( 含 )"),
    _10年(6,"5-10年 ( 含 )"),
    _15年(7,"10-15年 ( 含 )"),
    _15年以上(8,"15年以上");

    private int code;
    private String title;
    OperatorYearsEnum(int code, String title) {
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
        OperatorYearsEnum[] enums = OperatorYearsEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
        }
        return vals;
    }

    public static OperatorYearsEnum find(Integer code) {
        if(code == null) return null;
        OperatorYearsEnum[] enums = OperatorYearsEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
