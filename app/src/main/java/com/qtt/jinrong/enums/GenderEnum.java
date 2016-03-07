package com.qtt.jinrong.enums;

/**
 * Created by yanxin on 16/3/7.
 */
public enum GenderEnum {

    男(1),
    女(2);

    private int code;
    GenderEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
