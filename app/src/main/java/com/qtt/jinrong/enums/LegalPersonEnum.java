package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 法人股东
 * Created by yanxin on 16/3/4.
 */
public enum LegalPersonEnum {

    法人(1,"法人"),
    占股10的股东(2,"占股10%的股东"),
    占股20的股东(3,"占股20%的股东"),
    占股30的股东(4,"占股30%的股东"),
    占股50的股东(5,"占股50%的股东"),
    占股80的股东(6,"占股80%的股东");

    private int code;
    private String title;

    LegalPersonEnum(int code, String title) {
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
        LegalPersonEnum[] enums = LegalPersonEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static LegalPersonEnum find(Integer code) {
        if(code == null) return null;
        LegalPersonEnum[] enums = LegalPersonEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
