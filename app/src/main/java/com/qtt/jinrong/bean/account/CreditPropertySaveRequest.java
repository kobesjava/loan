package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class CreditPropertySaveRequest implements IRequest {

    private String userId;

    private int creInfo;

    private int creMoney;

    private int creNum;

    private String creBank;

    private int creUsed;

    private int creDebt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCreInfo() {
        return creInfo;
    }

    public void setCreInfo(int creInfo) {
        this.creInfo = creInfo;
    }

    public int getCreMoney() {
        return creMoney;
    }

    public void setCreMoney(int creMoney) {
        this.creMoney = creMoney;
    }

    public int getCreNum() {
        return creNum;
    }

    public void setCreNum(int creNum) {
        this.creNum = creNum;
    }

    public String getCreBank() {
        return creBank;
    }

    public void setCreBank(String creBank) {
        this.creBank = creBank;
    }

    public int getCreUsed() {
        return creUsed;
    }

    public void setCreUsed(int creUsed) {
        this.creUsed = creUsed;
    }

    public int getCreDebt() {
        return creDebt;
    }

    public void setCreDebt(int creDebt) {
        this.creDebt = creDebt;
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
