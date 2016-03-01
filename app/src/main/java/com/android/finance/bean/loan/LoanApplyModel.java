package com.android.finance.bean.loan;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanApplyModel extends LoanModel {

    private String applyId;

    private String status;

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
}
