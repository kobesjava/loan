package com.qtt.jinrong.bean.loan;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanApplyModel extends LoanModel {

    private String applyId;

    private int aomount;

    private String term;

    private String status;

    private String reason;

    private String source;

    private long time;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getAomount() {
        return aomount;
    }

    public void setAomount(int aomount) {
        this.aomount = aomount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
