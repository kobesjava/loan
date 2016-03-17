package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.enums.CreditDebtSituationEnum;
import com.qtt.jinrong.enums.CreditSituationEnum;
import com.qtt.jinrong.enums.CreditTotalLimitEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditPropertySaveRequest implements IRequest {

    private String userId;

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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("creInfo",creInfo);

        //CreditSituationEnum csEnum = CreditSituationEnum.find(creInfo);
        //if(csEnum != null && csEnum.equals(CreditSituationEnum.有逾期)) {
            map.put("overdue",overdue);
        //}

        map.put("creMoney",creMoney);

        //CreditTotalLimitEnum ctlEnum = CreditTotalLimitEnum.find(creMoney);
        //if(ctlEnum != null && !ctlEnum.equals(CreditTotalLimitEnum.无信用卡)) {
            map.put("creNum",creNum);
            map.put("creBank",creBank);
            map.put("creUsed",creUsed);
        //}

        map.put("creDebt",creDebt);

        //CreditDebtSituationEnum cdsEnum = CreditDebtSituationEnum.find(creDebt);
        //if(cdsEnum != null && !cdsEnum.equals(CreditDebtSituationEnum.无欠款)) {
            map.put("creDebtName",creDebtName);
            map.put("creDebtAmt",creDebtAmt);
            map.put("creMonthRepay",creMonthRepay);
        //}

        return map;
    }
}
