package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public enum HousePropertySituationEnum {

    已被抵押按揭给银行机构(1,"已被抵押/按揭给银行机构"),
    已被抵押按揭给非银行机构(2,"已被抵押/按揭给非银行机构"),
    未被抵押无按揭(3,"未被抵押/无按揭");

    private int code;
    private String title;

    HousePropertySituationEnum(int code, String title) {
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
        HousePropertySituationEnum[] enums = HousePropertySituationEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }
}
