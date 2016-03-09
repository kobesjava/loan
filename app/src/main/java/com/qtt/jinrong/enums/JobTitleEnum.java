package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 16/3/4.
 */
public enum JobTitleEnum {

    高级管理层(1),
    中级管理层(2),
    基层员工(3);

    private int code;
    JobTitleEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        JobTitleEnum[] enums = JobTitleEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

}
