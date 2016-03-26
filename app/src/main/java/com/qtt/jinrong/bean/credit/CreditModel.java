package com.qtt.jinrong.bean.credit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditModel implements Parcelable {

    private String id;
    private String thumpImg;
    private String creTitle;
    private String creDesc;
    //卡等级
    private Integer creClass;
    //creClass
    private String creQuota;
    //申请人数
    private Integer click;

    private String creFree;

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

    public String getCreDesc() {
        return creDesc;
    }

    public void setCreDesc(String creDesc) {
        this.creDesc = creDesc;
    }

    public Integer getCreClass() {
        return creClass;
    }

    public void setCreClass(Integer creClass) {
        this.creClass = creClass;
    }

    public String getCreQuota() {
        return creQuota;
    }

    public void setCreQuota(String creQuota) {
        this.creQuota = creQuota;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getCreFree() {
        return creFree;
    }

    public void setCreFree(String creFree) {
        this.creFree = creFree;
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
        dest.writeString(this.creDesc);
        dest.writeValue(this.creClass);
        dest.writeString(this.creQuota);
        dest.writeValue(this.click);
        dest.writeString(this.creFree);
    }

    public CreditModel() {
    }

    protected CreditModel(Parcel in) {
        this.id = in.readString();
        this.thumpImg = in.readString();
        this.creTitle = in.readString();
        this.creDesc = in.readString();
        this.creClass = (Integer) in.readValue(Integer.class.getClassLoader());
        this.creQuota = in.readString();
        this.click = (Integer) in.readValue(Integer.class.getClassLoader());
        this.creFree = in.readString();
    }

    public static final Parcelable.Creator<CreditModel> CREATOR = new Parcelable.Creator<CreditModel>() {
        public CreditModel createFromParcel(Parcel source) {
            return new CreditModel(source);
        }

        public CreditModel[] newArray(int size) {
            return new CreditModel[size];
        }
    };
}
