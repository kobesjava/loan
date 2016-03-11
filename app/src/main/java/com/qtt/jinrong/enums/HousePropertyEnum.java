package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 名下房产情况
 * Created by yanxin on 16/3/4.
 */
public enum HousePropertyEnum {

    无房产(1,"无房产"),
    商品住宅(2,"商品住宅"),
    商铺(3,"商铺"),
    办公楼(4,"办公楼"),
    厂房(5,"厂房"),
    经济限价房(6,"经济/限价房"),
    房改危改房(7,"房改/危改房"),
    小产权房(8,"小产权房"),
    宅基地自建房产(9,"宅基地/自建房产"),
    商住两用房(10,"商住两用房"),
    军产房(11,"军产房");

    private int code;
    private String title;

    HousePropertyEnum(int code, String title) {
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
        HousePropertyEnum[] enums = HousePropertyEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static HousePropertyEnum find(int code) {
        HousePropertyEnum[] enums = HousePropertyEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }
}
