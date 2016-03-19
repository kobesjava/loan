package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 行驶里程
 * Created by yanxin on 16/3/4.
 */
public enum CarDriveKmEnum {

    _1万公里(1,"0-1万公里 ( 含 )"),
    _3万公里(2,"1-3万公里 ( 含 )"),
    _5万公里(3,"3-5万公里 ( 含 )"),
    _7万公里(4,"5-7万公里 ( 含 )"),
    _10万公里(5,"7-10万公里 ( 含 )"),
    _15万公里(6,"10-15万公里 ( 含 )"),
    _20万公里(7,"15-20万公里 ( 含 )"),
    _20万公里以上(8,"20万公里以上");

    private int code;
    private String title;
    CarDriveKmEnum(int code, String title) {
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
        CarDriveKmEnum[] enums = CarDriveKmEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
        }
        return vals;
    }

    public static CarDriveKmEnum find(Integer code) {
        if(code == null) return null;
        CarDriveKmEnum[] enums = CarDriveKmEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
