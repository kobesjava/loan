package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 房产地段
 * Created by yanxin on 16/3/4.
 */
public enum HousePropertyPositionEnum {

    中心城区(1),
    郊区(2),
    外地(3);

    private int code;
    HousePropertyPositionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        HousePropertyPositionEnum[] enums = HousePropertyPositionEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static HousePropertyPositionEnum find(Integer code) {
        if(code == null) return null;
        HousePropertyPositionEnum[] enums = HousePropertyPositionEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
