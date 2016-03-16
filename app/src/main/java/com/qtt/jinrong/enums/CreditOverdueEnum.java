package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 信用逾期情况
 * Created by yanxin on 16/3/4.
 */
public enum CreditOverdueEnum {

    当出现逾期两次未偿还的记录(1),
    近半年内出现逾期三期情况(2),
    近半年出现两次逾期两期情况(3),
    一年内出现过逾期四期及以上(4);

    private int code;
    CreditOverdueEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        CreditOverdueEnum[] enums = CreditOverdueEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static CreditOverdueEnum find(Integer code) {
        if(code == null) return null;
        CreditOverdueEnum[] enums = CreditOverdueEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }

}
