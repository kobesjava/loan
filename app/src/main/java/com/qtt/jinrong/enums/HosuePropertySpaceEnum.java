package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 房产面积
 * Created by yanxin on 16/3/7.
 */
public enum HosuePropertySpaceEnum {

    SPACE_50(1,"0-50平米"),
    SPACE_75(2,"51-75平米"),
    SPACE_90(3,"76-90平米"),
    SPACE_100(4,"91-100平米"),
    SPACE_120(5,"101-120平米"),
    SPACE_150(6,"121-150平米"),
    SPACE_150以上(7,"150平米以上");

    private int code;
    private String title;

    HosuePropertySpaceEnum(int code, String title) {
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
        HosuePropertySpaceEnum[] enums = HosuePropertySpaceEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }
}
