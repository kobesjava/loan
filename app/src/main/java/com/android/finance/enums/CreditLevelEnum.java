package com.android.finance.enums;

/**
 * Created by yanxin on 16/2/29.
 */
public enum CreditLevelEnum {

    不限(0),
    普卡(1),
    银卡(2),
    金卡(3),
    钛金卡(4),
    贵宾卡(5),
    白金卡(6),
    钻石卡(7),
    铂金卡(8),
    无限卡(9),
    世界卡(10);

    private int code;

    CreditLevelEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CreditLevelEnum find(int level) {
        CreditLevelEnum[] levels = values();
        for(int i=0;i<levels.length;i++) {
            if(levels[i].code == level) return levels[i];
        }
        return null;
    }
}
