package com.qtt.jinrong.bean.loan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/2/24.
 */
public class LoanApplyModel implements Parcelable{

    private String id;
    private String productId;
    private String thumpImg;
    private String title;
    //期限
    private Integer expires;
    /** 金额*/
    private Integer money;
    //状态
    private Integer status;
    //申请来源
    private Integer applySrc;
    //时间
    private String applyDate;
    private String handleDate;
    //原因
    private String handleReason;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApplySrc() {
        return applySrc;
    }

    public void setApplySrc(Integer applySrc) {
        this.applySrc = applySrc;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getHandleReason() {
        return handleReason;
    }

    public void setHandleReason(String handleReason) {
        this.handleReason = handleReason;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public LoanApplyModel() {
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
        dest.writeValue(this.expires);
        dest.writeValue(this.money);
        dest.writeInt(this.status);
        dest.writeInt(this.applySrc);
        dest.writeString(this.applyDate);
        dest.writeString(this.handleDate);
        dest.writeString(this.handleReason);
    }

    protected LoanApplyModel(Parcel in) {
        this.id = in.readString();
        this.productId = in.readString();
        this.thumpImg = in.readString();
        this.title = in.readString();
        this.expires = (Integer) in.readValue(Integer.class.getClassLoader());
        this.money = (Integer) in.readValue(Integer.class.getClassLoader());
        this.status = in.readInt();
        this.applySrc = in.readInt();
        this.applyDate = in.readString();
        this.handleDate = in.readString();
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
