package com.qtt.jinrong.bean.loan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanModel implements Parcelable {

    private String id;
    private String productId;
    /** 图片地址*/
    private String thumpImg;
    /** 名称*/
    private String title;
    /** 所属公司*/
    private String ownedCompany;
    /** 月利率*/
    public String monthRate;
    /** 总利息*/
    private String rate;
    /** 月供*/
    private String money;
    /** 评分*/
    private float score;
    /** 是否是复利*/
    public boolean compound;
    /** 月管理费用*/
    public int monthManageFee;
    /** 一次性管理费用*/
    public int onceManageFee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getThumpImg() {
        return thumpImg;
    }

    public void setThumpImg(String thumpImg) {
        this.thumpImg = thumpImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnedCompany() {
        return ownedCompany;
    }

    public void setOwnedCompany(String ownedCompany) {
        this.ownedCompany = ownedCompany;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public LoanModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.productId);
        dest.writeString(this.thumpImg);
        dest.writeString(this.title);
        dest.writeString(this.ownedCompany);
        dest.writeString(this.monthRate);
        dest.writeString(this.rate);
        dest.writeString(this.money);
        dest.writeFloat(this.score);
        dest.writeByte(compound ? (byte) 1 : (byte) 0);
    }

    protected LoanModel(Parcel in) {
        this.id = in.readString();
        this.productId = in.readString();
        this.thumpImg = in.readString();
        this.title = in.readString();
        this.ownedCompany = in.readString();
        this.monthRate = in.readString();
        this.rate = in.readString();
        this.money = in.readString();
        this.score = in.readFloat();
        this.compound = in.readByte() != 0;
    }

    public static final Creator<LoanModel> CREATOR = new Creator<LoanModel>() {
        public LoanModel createFromParcel(Parcel source) {
            return new LoanModel(source);
        }

        public LoanModel[] newArray(int size) {
            return new LoanModel[size];
        }
    };
}
