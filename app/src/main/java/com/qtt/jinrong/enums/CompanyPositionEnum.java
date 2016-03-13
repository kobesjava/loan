package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业所在地
 * Created by yanxin on 16/2/24.
 */
public enum CompanyPositionEnum {

    本地(1),
    外地(2);

    private int code;

    CompanyPositionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static List<String> getValues() {
        List<String> titles = new ArrayList<>(5);
        CompanyPositionEnum[] enums = CompanyPositionEnum.values();
        for(int i=0;i<enums.length;i++) {
            titles.add(enums[i].name());
        }
        return titles;
    }
}
