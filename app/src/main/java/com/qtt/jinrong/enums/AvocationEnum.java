package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 有无副业
 * Created by yanxin on 16/3/4.
 */
public enum AvocationEnum {

    有副业(1),
    无副业(2);

    private int code;
    AvocationEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        AvocationEnum[] enums = AvocationEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static AvocationEnum find(Integer code) {
        if(code == null) return null;
        AvocationEnum[] enums = AvocationEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
