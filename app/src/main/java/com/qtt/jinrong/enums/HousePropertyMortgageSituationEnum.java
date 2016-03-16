package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 房产抵押情况
 * Created by yanxin on 16/3/4.
 */
public enum HousePropertyMortgageSituationEnum {

    已被抵押按揭给银行机构(1,"已被抵押/按揭给银行机构"),
    已被抵押按揭给非银行机构(2,"已被抵押/按揭给非银行机构"),
    未被抵押无按揭(3,"未被抵押/无按揭");

    private int code;
    private String title;

    HousePropertyMortgageSituationEnum(int code, String title) {
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
        HousePropertyMortgageSituationEnum[] enums = HousePropertyMortgageSituationEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static HousePropertyMortgageSituationEnum find(Integer code) {
        if(code == null) return null;
        HousePropertyMortgageSituationEnum[] enums = HousePropertyMortgageSituationEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
