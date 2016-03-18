package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业欠款情况
 * Created by yanxin on 16/3/4.
 */
public enum DebtSituationEnterpriseEnum {

    无欠款(1),
    有银行欠款(2),
    有小贷公司欠款(3),
    有民间欠款(4),
    有其它类型欠款(5);

    private int code;
    DebtSituationEnterpriseEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        DebtSituationEnterpriseEnum[] enums = DebtSituationEnterpriseEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static DebtSituationEnterpriseEnum find(Integer code) {
        if(code == null) return null;
        DebtSituationEnterpriseEnum[] enums = DebtSituationEnterpriseEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
