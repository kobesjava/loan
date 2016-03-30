package com.qtt.jinrong.enums;

/**
 * 申请来源
 * Created by yanxin on 16/3/27.
 */
public enum ApplySourceEnum {

    自申请(0);

    private int code;

    ApplySourceEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ApplySourceEnum find(Integer code) {
        if(code == null) return null;

        ApplySourceEnum[] enums = values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }

        return null;
    }

}
