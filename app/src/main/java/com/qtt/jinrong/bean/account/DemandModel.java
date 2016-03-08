package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class DemandModel {

    /** 贷款金额*/
    private Float loMoney;
    /** 贷款期限*/
    private Integer loExpires;
    /** 用款时间*/
    private String loDate;
    /** 贷款用途*/
    private Integer loPurpose;
    /** 还款来源*/
    private Integer loPaymentSrc;
    /** 还款方式*/
    private Integer loPaymentWay;

    public Float getLoMoney() {
        return loMoney;
    }

    public void setLoMoney(Float loMoney) {
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
}
