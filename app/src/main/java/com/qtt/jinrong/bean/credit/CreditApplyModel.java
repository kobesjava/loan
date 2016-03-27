package com.qtt.jinrong.bean.credit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditApplyModel implements Parcelable {

    //信用卡申请ID
    private String creditId;
    //信用卡ID
    private String creId;
    private String thumpImg;
    private String creditName;
    private String quota;
    private String name;
    private String bank;
    private Integer state;
    private String type;
    private Long apptime;
    private String applyDate;


    public String getThumpImg() {
        return thumpImg;
    }

    public void setThumpImg(String thumpImg) {
        this.thumpImg = thumpImg;
    }

    public String getCreId() {
        return creId;
    }

    public void setCreId(String creId) {
        this.creId = creId;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getApptime() {
        return apptime;
    }

    public void setApptime(Long apptime) {
        this.apptime = apptime;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public CreditApplyModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.creditId);
        dest.writeString(this.creId);
        dest.writeString(this.thumpImg);
        dest.writeString(this.creditName);
        dest.writeString(this.quota);
        dest.writeString(this.name);
        dest.writeString(this.bank);
        dest.writeValue(this.state);
        dest.writeString(this.type);
        dest.writeValue(this.apptime);
        dest.writeString(this.applyDate);
    }

    protected CreditApplyModel(Parcel in) {
        this.creditId = in.readString();
        this.creId = in.readString();
        this.thumpImg = in.readString();
        this.creditName = in.readString();
        this.quota = in.readString();
        this.name = in.readString();
        this.bank = in.readString();
        this.state = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = in.readString();
        this.apptime = (Long) in.readValue(Long.class.getClassLoader());
        this.applyDate = in.readString();
    }

    public static final Creator<CreditApplyModel> CREATOR = new Creator<CreditApplyModel>() {
        public CreditApplyModel createFromParcel(Parcel source) {
            return new CreditApplyModel(source);
        }

        public CreditApplyModel[] newArray(int size) {
            return new CreditApplyModel[size];
        }
    };
}
