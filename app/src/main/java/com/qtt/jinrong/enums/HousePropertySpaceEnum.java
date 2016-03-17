package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 房产面积
 * Created by yanxin on 16/3/7.
 */
public enum HousePropertySpaceEnum {

    SPACE_50(1,"0-50平米"),
    SPACE_75(2,"51-75平米"),
    SPACE_90(3,"76-90平米"),
    SPACE_100(4,"91-100平米"),
    SPACE_120(5,"101-120平米"),
    SPACE_150(6,"121-150平米"),
    SPACE_150以上(7,"150平米以上");

    private int code;
    private String title;

    HousePropertySpaceEnum(int code, String title) {
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
        HousePropertySpaceEnum[] enums = HousePropertySpaceEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static HousePropertySpaceEnum find(Integer code) {
        if(code == null) return null;
        HousePropertySpaceEnum[] enums = HousePropertySpaceEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
