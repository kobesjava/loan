package com.qtt.jinrong.bean.loan;

/**
 * Created by yanxin on 16/3/8.
 */
public class LoanProductDetail {

    private String rate;

    private String exp;

    private int amount = 10;

    private int expires = 12;

    private int amountLow;

    private int amountHigh;

    private int expiresLow;

    private int expiresHigh;

    private String complete;

    private String rateDetail;

    private String apply;

    private String mater;

    private String repay;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public int getAmountLow() {
        return amountLow;
    }

    public void setAmountLow(int amountLow) {
        this.amountLow = amountLow;
    }

    public int getAmountHigh() {
        return amountHigh;
    }

    public void setAmountHigh(int amountHigh) {
        this.amountHigh = amountHigh;
    }

    public int getExpiresLow() {
        return expiresLow;
    }

    public void setExpiresLow(int expiresLow) {
        this.expiresLow = expiresLow;
    }

    public int getExpiresHigh() {
        return expiresHigh;
    }

    public void setExpiresHigh(int expiresHigh) {
        this.expiresHigh = expiresHigh;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getRateDetail() {
        return rateDetail;
    }

    public void setRateDetail(String rateDetail) {
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
