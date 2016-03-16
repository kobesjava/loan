package com.qtt.jinrong.bean.loan;

import android.os.Parcel;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.applyId);
        dest.writeInt(this.aomount);
        dest.writeString(this.term);
        dest.writeString(this.status);
        dest.writeString(this.reason);
        dest.writeString(this.source);
        dest.writeLong(this.time);
    }

    public LoanApplyModel() {
    }

    protected LoanApplyModel(Parcel in) {
        super(in);
        this.applyId = in.readString();
        this.aomount = in.readInt();
        this.term = in.readString();
        this.status = in.readString();
        this.reason = in.readString();
        this.source = in.readString();
        this.time = in.readLong();
    }

    public static final Creator<LoanApplyModel> CREATOR = new Creator<LoanApplyModel>() {
        @Override
        public LoanApplyModel createFromParcel(Parcel source) {
            return new LoanApplyModel(source);
        }

        @Override
        public LoanApplyModel[] newArray(int size) {
            return new LoanApplyModel[size];
        }
    };
}
