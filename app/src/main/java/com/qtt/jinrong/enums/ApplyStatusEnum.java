package com.qtt.jinrong.enums;

/**
 * 申请状态
 * Created by yanxin on 16/3/27.
 */
public enum ApplyStatusEnum {

    审核中(0),
    已受理(1);

    private int code;

    ApplyStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ApplyStatusEnum find(Integer code) {
        if(code == null) return null;

        ApplyStatusEnum[] enums = values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }

        return null;
    }

}
