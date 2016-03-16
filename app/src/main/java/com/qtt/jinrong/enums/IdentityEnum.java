package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 身份
 * Created by yanxin on 16/3/4.
 */
public enum IdentityEnum {

    企业户(1),
    个体户(2),
    工薪族(3),
    其他(4);

    private int code;
    IdentityEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        IdentityEnum[] enums = IdentityEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static IdentityEnum find(Integer code) {
        if(code == null) return null;
        IdentityEnum[] enums = IdentityEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
