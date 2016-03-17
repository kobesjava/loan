package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 还款来源
 * Created by yanxin on 16/3/4.
 */
public enum RepaymentSourceEnum {

    应收账款(1),
    处理存货(2),
    变卖固定资产(3),
    其它(4);

    private int code;
    RepaymentSourceEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        RepaymentSourceEnum[] enums = RepaymentSourceEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static RepaymentSourceEnum find(Integer code) {
        if(code == null) return null;
        RepaymentSourceEnum[] enums = RepaymentSourceEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
