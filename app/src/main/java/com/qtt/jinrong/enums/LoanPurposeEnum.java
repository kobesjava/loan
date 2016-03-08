package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public enum LoanPurposeEnum {

    补充流动资金(1),
    项目融资(2),
    研发投入(3),
    技术改造(4),
    其它(5);

    private int code;
    LoanPurposeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        LoanPurposeEnum[] enums = LoanPurposeEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static LoanPurposeEnum find(int code) {
        LoanPurposeEnum[] enums = LoanPurposeEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code) return enums[i];
        }
        return null;
    }

}
