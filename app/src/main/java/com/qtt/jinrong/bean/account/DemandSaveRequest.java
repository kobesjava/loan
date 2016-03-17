package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class DemandSaveRequest implements IRequest {

    private String userId;
    //贷款金额
    private Integer loMoney;
    //贷款期限
    private Integer loExpires;
    //用款时间
    private String loDate;
    //贷款用途
    private Integer loPurpose;
    //还款来源
    private Integer loPaymentSrc;
    //还款方式
    private Integer loPaymentWay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getLoMoney() {
        return loMoney;
    }

    public void setLoMoney(Integer loMoney) {
        this.loMoney = loMoney;
    }

    public Integer getLoExpires() {
        return loExpires;
    }

    public void setLoExpires(Integer loExpires) {
        this.loExpires = loExpires;
    }

    public String getLoDate() {
        return loDate;
    }

    public void setLoDate(String loDate) {
        this.loDate = loDate;
    }

    public Integer getLoPurpose() {
        return loPurpose;
    }

    public void setLoPurpose(Integer loPurpose) {
        this.loPurpose = loPurpose;
    }

    public Integer getLoPaymentSrc() {
        return loPaymentSrc;
    }

    public void setLoPaymentSrc(Integer loPaymentSrc) {
        this.loPaymentSrc = loPaymentSrc;
    }

    public Integer getLoPaymentWay() {
        return loPaymentWay;
    }

    public void setLoPaymentWay(Integer loPaymentWay) {
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
