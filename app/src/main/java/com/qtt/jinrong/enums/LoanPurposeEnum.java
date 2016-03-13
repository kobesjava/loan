package com.qtt.jinrong.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 贷款用途
 * Created by yanxin on 16/3/4.
 */
public enum LoanPurposeEnum {

    补充流动资金(1,"经营-补充流动资金"),
    项目融资(2,"经营-贸易融资"),
    研发投入(3,"经营-供应链融资"),
    技术改造(4,"经营-其他投资"),
    其它(5,"其它"),
    购车(6,"消费-购车"),
    购房(7,"消费-购房"),
    装修(8,"消费-装修"),
    旅游(9,"消费-旅游"),
    教育(10,"消费-教育"),
    医疗美容(11,"消费-医疗美容"),
    购买设备(12,"经营-购买设备"),
    承兑汇票(13,"经营-承兑汇票"),
    厂房装修(14,"经营-厂房装修"),
    过桥垫资(15,"短期周转-过桥/垫资"),
    代还信用卡(16,"短期周转-代还信用卡"),
    应急贷款(17,"应急贷款");

    private int code;
    private String title;
    LoanPurposeEnum(int code,String title) {
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
        LoanPurposeEnum[] enums = LoanPurposeEnum.values();
        List<String> vals = new ArrayList<>();
        for(int i=0;i<enums.length;i++) {
            vals.add(enums[i].getTitle());
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
