package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditPropertySaveRequest implements IRequest,Cloneable {

    private String userId;

    private Integer creInfo;

    private Integer creMoney;

    private Integer creNum;

    private String creBank;

    private Integer creUsed;

    private Integer creDebt;


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

    @Override
    public CreditPropertySaveRequest clone() {
        try {
            return (CreditPropertySaveRequest) super.clone();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("creInfo",creInfo);
        map.put("creMoney",creMoney);
        map.put("creNum",creNum);
        map.put("creBank",creBank);
        map.put("creUsed",creUsed);
        map.put("creDebt",creDebt);
        return map;
    }
}
