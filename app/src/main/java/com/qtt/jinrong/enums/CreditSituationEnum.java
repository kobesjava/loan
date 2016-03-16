package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 信用情况
 * Created by yanxin on 16/3/4.
 */
public enum CreditSituationEnum {

    无贷款或信用卡(1,"无贷款或信用卡"),
    记录良好无逾期(2,"记录良好,无逾期"),
    有逾期(3,"有逾期");

    private int code;
    private String title;

    CreditSituationEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public static List<String> getValues() {
        List<String> titles = new ArrayList<>(5);
        CreditSituationEnum[] enums = CreditSituationEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].getTitle());
        }
        return titles;
    }

    public static CreditSituationEnum find(Integer code) {
        if(code == null) return null;
        CreditSituationEnum[] enums = CreditSituationEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
