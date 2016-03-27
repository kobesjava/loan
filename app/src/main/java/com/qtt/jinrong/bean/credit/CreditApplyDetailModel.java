package com.qtt.jinrong.bean.credit;

/**
 * Created by yanxin on 16/3/27.
 */
public class CreditApplyDetailModel {

    private Integer status;

    private String handleDate;
    //原因
    private String handleReason;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getHandleReason() {
        return handleReason;
    }

    public void setHandleReason(String handleReason) {
        this.handleReason = handleReason;
    }
}
