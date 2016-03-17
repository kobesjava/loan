package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditPropertyModel {

    /** 信用情况*/
    private Integer creInfo;
    /** 逾期情况*/
    private Integer overdue;
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
    /** 欠款机构名称*/
    private String creDebtName;
    /** 欠款余额*/
    private Integer creDebtAmt;
    /** 月均还款额*/
    private Integer creMonthRepay;


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

    public Integer getOverdue() {
        return overdue;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public String getCreDebtName() {
        return creDebtName;
    }

    public void setCreDebtName(String creDebtName) {
        this.creDebtName = creDebtName;
    }

    public Integer getCreDebtAmt() {
        return creDebtAmt;
    }

    public void setCreDebtAmt(Integer creDebtAmt) {
        this.creDebtAmt = creDebtAmt;
    }

    public Integer getCreMonthRepay() {
        return creMonthRepay;
    }

    public void setCreMonthRepay(Integer creMonthRepay) {
        this.creMonthRepay = creMonthRepay;
    }
}
