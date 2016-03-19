package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 能否提供收入证明
 * Created by yanxin on 16/3/4.
 */
public enum IncomeProofEnum {

    能证明(1),
    不能提供(2);

    private int code;
    IncomeProofEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        IncomeProofEnum[] enums = IncomeProofEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static IncomeProofEnum find(Integer code) {
        if(code == null) return null;
        IncomeProofEnum[] enums = IncomeProofEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
