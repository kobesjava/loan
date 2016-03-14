package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditPropertyModel {

    /** 信用情况*/
    private Integer creInfo;
    /** 信用卡总额度*/
    private Integer creMoney;
    /** 信用卡张数*/
    private Integer creNum;
    /** 发卡银行*/
    private String creBank;
    /** 已使用额度*/
    private Integer creUsed;
    /** 欠款情况*/
    private Integer creDebt;


    public Integer getCreInfo() {
        return creInfo;
    }

    public void setCreInfo(Integer creInfo) {
        this.creInfo = creInfo;
    }

    public Integer getCreMoney() {
        return creMoney;
    }

    public void setCreMoney(Integer creMoney) {
        this.creMoney = creMoney;
    }

    public Integer getCreNum() {
        return creNum;
    }

    public void setCreNum(Integer creNum) {
        this.creNum = creNum;
    }

    public String getCreBank() {
        return creBank;
    }

    public void setCreBank(String creBank) {
        this.creBank = creBank;
    }

    public Integer getCreUsed() {
        return creUsed;
    }

    public void setCreUsed(Integer creUsed) {
        this.creUsed = creUsed;
    }

    public Integer getCreDebt() {
        return creDebt;
    }

    public void setCreDebt(Integer creDebt) {
        this.creDebt = creDebt;
    }
}
