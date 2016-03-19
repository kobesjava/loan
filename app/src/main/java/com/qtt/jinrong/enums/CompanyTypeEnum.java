package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司类型
 * Created by yanxin on 16/3/4.
 */
public enum CompanyTypeEnum {

    政府机构事业单位(1,"政府机构/事业单位"),
    国企央企(2,"国企央企"),
    外资企业(3,"外资企业"),
    上市公司(4,"上市公司"),
    普通民营企业(5,"普通民营企业"),
    个体工商业户(6,"个体工商业户");

    private int code;
    private String title;
    CompanyTypeEnum(int code,String title) {
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
        CompanyTypeEnum[] enums = CompanyTypeEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
        }
        return vals;
    }

    public static CompanyTypeEnum find(Integer code) {
        if(code == null) return null;
        CompanyTypeEnum[] enums = CompanyTypeEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
