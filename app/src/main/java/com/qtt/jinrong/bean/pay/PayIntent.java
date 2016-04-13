package com.qtt.jinrong.bean.pay;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/4/12.
 */
public class PayIntent implements Parcelable{

    private String title;

    private Integer amount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeValue(this.amount);
    }

    public PayIntent() {
    }

    protected PayIntent(Parcel in) {
        this.title = in.readString();
        this.amount = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<PayIntent> CREATOR = new Creator<PayIntent>() {
        @Override
        public PayIntent createFromParcel(Parcel source) {
            return new PayIntent(source);
        }

        @Override
        public PayIntent[] newArray(int size) {
            return new PayIntent[size];
        }
    };
}
