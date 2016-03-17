package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 配偶能否出面担保/签字
 * Created by yanxin on 16/3/13.
 */
public enum SpouseGuaranteeEnum {

    能(1),
    不能(2);

    private int code;
    SpouseGuaranteeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        SpouseGuaranteeEnum[] enums = SpouseGuaranteeEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static SpouseGuaranteeEnum find(Integer code) {
        if(code == null) return null;
        SpouseGuaranteeEnum[] enums = SpouseGuaranteeEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
