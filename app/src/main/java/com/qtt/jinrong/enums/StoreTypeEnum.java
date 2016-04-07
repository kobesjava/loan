package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺类型
 * Created by yanxin on 16/3/4.
 */
public enum StoreTypeEnum {

    天猫淘宝(1,"天猫/淘宝"),
    京东阿里巴巴(2,"京东/阿里巴巴"),
    ebay(3,"ebay"),
    一通百(4,"一通百"),
    Lazada(5,"Lazada");

    private int code;
    private String title;

    StoreTypeEnum(int code, String title) {
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
        StoreTypeEnum[] enums = StoreTypeEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static StoreTypeEnum find(Integer code) {
        if(code == null) return null;
        StoreTypeEnum[] enums = StoreTypeEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
