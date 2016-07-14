package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 资料类型
 * Created by yanxin on 16/3/4.
 */
public enum DataTypeEnum {

    身份证正面(1,0),
    身份证反面(2,1),
    营业执照(3,2),
    个人头像(4,3);

    private int code;
    private int index;
    DataTypeEnum(int code,int index) {
        this.code = code;
        this.index = index;
    }

    public int getCode() {
        return code;
    }

    public int getIndex() {
        return index;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static List<String> getValues() {
        DataTypeEnum[] enums = DataTypeEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].name());
        }
        return vals;
    }

    public static List<Integer> getCodes() {
        DataTypeEnum[] enums = DataTypeEnum.values();
        List<Integer> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getCode());
        }
        return vals;
    }

    public static DataTypeEnum find(Integer code) {
        if(code == null) return null;
        DataTypeEnum[] enums = DataTypeEnum.values();
        for(int i=0;i<enums.length;i++) {
            if(enums[i].getCode() == code.intValue()) return enums[i];
        }
        return null;
    }
}
