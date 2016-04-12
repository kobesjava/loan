package com.qtt.jinrong.enums;

/**
 * 会员
 * Created by yanxin on 16/4/12.
 */
public enum MemberEnum {

    铜牌("铜牌会员",499),
    银牌("银牌会员",1499),
    金牌("金牌会员",4999);

    private String level;
    private int amount;

    public String getLevel() {
        return level;
    }

    public int getAmount() {
        return amount;
    }

    MemberEnum(String level,int amount) {
        this.level = level;
        this.amount = amount;
    }

}
