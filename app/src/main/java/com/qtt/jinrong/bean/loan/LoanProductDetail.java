package com.qtt.jinrong.bean.loan;

/**
 * Created by yanxin on 16/3/8.
 */
public class LoanProductDetail {

    /** 优点*/
    private String exp;
    /** 金额*/
    private Integer amount;
    /** 期限*/
    private Integer expires;
    /** 最低月利率*/
    private Float rateLow;
    /** 最高月利率*/
    private Float rateHigh;
    private Integer amountLow;
    private Integer amountHigh;
    private Integer expiresLow;
    private Integer expiresHigh;
    /** 放款时间*/
    private String complete;
    /** 参考服务费率*/
    private Float rateDetail;
    /** 申请条件*/
    private String apply;
    /** 所需资料*/
    private String mater;
    /** 还款方式*/
    private String repay;

    public Float getRateLow() {
        return rateLow;
    }

    public void setRateLow(Float rateLow) {
        this.rateLow = rateLow;
    }

    public Float getRateHigh() {
        return rateHigh;
    }

    public void setRateHigh(Float rateHigh) {
        this.rateHigh = rateHigh;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public Integer getAmountLow() {
        return amountLow;
    }

    public void setAmountLow(Integer amountLow) {
        this.amountLow = amountLow;
    }

    public Integer getAmountHigh() {
        return amountHigh;
    }

    public void setAmountHigh(Integer amountHigh) {
        this.amountHigh = amountHigh;
    }

    public Integer getExpiresLow() {
        return expiresLow;
    }

    public void setExpiresLow(Integer expiresLow) {
        this.expiresLow = expiresLow;
    }

    public Integer getExpiresHigh() {
        return expiresHigh;
    }

    public void setExpiresHigh(Integer expiresHigh) {
        this.expiresHigh = expiresHigh;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public Float getRateDetail() {
        return rateDetail;
    }

    public void setRateDetail(Float rateDetail) {
        this.rateDetail = rateDetail;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getMater() {
        return mater;
    }

    public void setMater(String mater) {
        this.mater = mater;
    }

    public String getRepay() {
        return repay;
    }

    public void setRepay(String repay) {
        this.repay = repay;
    }
}
