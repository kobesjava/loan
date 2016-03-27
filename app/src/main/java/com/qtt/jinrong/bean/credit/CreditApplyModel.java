package com.qtt.jinrong.bean.credit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditApplyModel implements Parcelable {

    private String id;
    private String thumpImg;
    private String creTitle;
    //卡等级
    private String creClass;
    private String creBank;
    private String creType;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumpImg() {
        return thumpImg;
    }

    public void setThumpImg(String thumpImg) {
        this.thumpImg = thumpImg;
    }

    public String getCreTitle() {
        return creTitle;
    }

    public void setCreTitle(String creTitle) {
        this.creTitle = creTitle;
    }

    public String getCreClass() {
        return creClass;
    }

    public void setCreClass(String creClass) {
        this.creClass = creClass;
    }

    public String getCreBank() {
        return creBank;
    }

    public void setCreBank(String creBank) {
        this.creBank = creBank;
    }

    public String getCreType() {
        return creType;
    }

    public void setCreType(String creType) {
        this.creType = creType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CreditApplyModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.thumpImg);
        dest.writeString(this.creTitle);
        dest.writeString(this.creClass);
        dest.writeString(this.creBank);
        dest.writeString(this.creType);
        dest.writeString(this.time);
    }

    protected CreditApplyModel(Parcel in) {
        this.id = in.readString();
        this.thumpImg = in.readString();
        this.creTitle = in.readString();
        this.creClass = in.readString();
        this.creBank = in.readString();
        this.creType = in.readString();
        this.time = in.readString();
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
