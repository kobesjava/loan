package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class DemandSaveRequest implements IRequest {

    private String userId;

    private float loMoney;

    private int loExpires;

    private String loDate;

    private int loPurpose;

    private int loPaymentSrc;

    private int loPaymentWay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getLoMoney() {
        return loMoney;
    }

    public void setLoMoney(float loMoney) {
        this.loMoney = loMoney;
    }

    public int getLoExpires() {
        return loExpires;
    }

    public void setLoExpires(int loExpires) {
        this.loExpires = loExpires;
    }

    public String getLoDate() {
        return loDate;
    }

    public void setLoDate(String loDate) {
        this.loDate = loDate;
    }

    public int getLoPurpose() {
        return loPurpose;
    }

    public void setLoPurpose(int loPurpose) {
        this.loPurpose = loPurpose;
    }

    public int getLoPaymentSrc() {
        return loPaymentSrc;
    }

    public void setLoPaymentSrc(int loPaymentSrc) {
        this.loPaymentSrc = loPaymentSrc;
    }

    public int getLoPaymentWay() {
        return loPaymentWay;
    }

    public void setLoPaymentWay(int loPaymentWay) {
        this.loPaymentWay = loPaymentWay;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("loMoney",loMoney);
        map.put("loExpires",loExpires);
        map.put("loDate",loDate);
        map.put("loPurpose",loPurpose);
        map.put("loPaymentSrc",loPaymentSrc);
        map.put("loPaymentWay",loPaymentWay);
        return map;
    }
}
