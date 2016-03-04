package com.qtt.jinrong.enums;

/**
 * Created by yanxin on 16/3/4.
 */
public enum CitySHEnum {

    上海市(1);

    private int code;
    CitySHEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
