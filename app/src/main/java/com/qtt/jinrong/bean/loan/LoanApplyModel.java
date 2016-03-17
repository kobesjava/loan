package com.qtt.jinrong.bean.loan;

import android.os.Parcel;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanApplyModel extends LoanModel {

    //金额
    private int aomount;
    //期限
    private String applyLimi;
    //状态
    private String status;
    //申请来源
    private String applySrc;
    //时间
    private long applyDate;
    //原因
    private String handleReason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAomount() {
        return aomount;
    }

    public void setAomount(int aomount) {
        this.aomount = aomount;
    }

    public String getApplyLimi() {
        return applyLimi;
    }

    public void setApplyLimi(String applyLimi) {
        this.applyLimi = applyLimi;
    }

    public String getApplySrc() {
        return applySrc;
    }

    public void setApplySrc(String applySrc) {
        this.applySrc = applySrc;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    public String getHandleReason() {
        return handleReason;
    }

    public void setHandleReason(String handleReason) {
        this.handleReason = handleReason;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.aomount);
        dest.writeString(this.applyLimi);
        dest.writeString(this.status);
        dest.writeString(this.applySrc);
        dest.writeLong(this.applyDate);
        dest.writeString(this.handleReason);
    }

    public LoanApplyModel() {
    }

    protected LoanApplyModel(Parcel in) {
        super(in);
        this.aomount = in.readInt();
        this.applyLimi = in.readString();
        this.status = in.readString();
        this.applySrc = in.readString();
        this.applyDate = in.readLong();
        this.handleReason = in.readString();
    }

    public static final Creator<LoanApplyModel> CREATOR = new Creator<LoanApplyModel>() {
        public LoanApplyModel createFromParcel(Parcel source) {
            return new LoanApplyModel(source);
        }

        public LoanApplyModel[] newArray(int size) {
            return new LoanApplyModel[size];
        }
    };
}
