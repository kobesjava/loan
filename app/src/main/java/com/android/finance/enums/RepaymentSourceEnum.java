package com.android.finance.enums;

import java.util.ArrayList;
import java.util.List;

/**
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

}
