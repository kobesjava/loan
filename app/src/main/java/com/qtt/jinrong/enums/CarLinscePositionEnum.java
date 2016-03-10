package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 牌照归属地
 * Created by yanxin on 16/3/4.
 */
public enum CarLinscePositionEnum {

    本地(1),
    外地(2);

    private int code;
    CarLinscePositionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        CarLinscePositionEnum[] enums = CarLinscePositionEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static CarLinscePositionEnum find(int code) {
        CarLinscePositionEnum[] enums = CarLinscePositionEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }

}
