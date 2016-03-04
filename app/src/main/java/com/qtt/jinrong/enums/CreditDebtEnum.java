package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public enum CreditDebtEnum {

    无欠款(1),
    有银行欠款(2),
    有小贷公司欠款(3),
    有民间欠款(4),
    有其它类型欠款(5);

    private int code;
    CreditDebtEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        CreditDebtEnum[] enums = CreditDebtEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

}
