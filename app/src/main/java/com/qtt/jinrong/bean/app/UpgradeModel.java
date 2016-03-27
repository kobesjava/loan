package com.qtt.jinrong.bean.app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/3/4.
 */
public class UpgradeModel implements Parcelable{

    /** 下载地址*/
    private String downUrl = "";
    /** 更新信息*/
    private String updateInfo = "";
    private String versionName = "";
    /** 版本号*/
    private String versionNum = "";
    /** 是否强制执行*/
    private boolean isMandatoryUpdate;
    /** 是否更新*/
    private boolean isUpdate;

    public boolean needUpgrade() {
        return isMandatoryUpdate || isUpdate;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public boolean isMandatoryUpdate() {
        return isMandatoryUpdate;
    }

    public void setIsMandatoryUpdate(boolean isMandatoryUpdate) {
        this.isMandatoryUpdate = isMandatoryUpdate;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public UpgradeModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.downUrl);
        dest.writeString(this.updateInfo);
        dest.writeString(this.versionName);
        dest.writeString(this.versionNum);
        dest.writeByte(isMandatoryUpdate ? (byte) 1 : (byte) 0);
        dest.writeByte(isUpdate ? (byte) 1 : (byte) 0);
    }

    protected UpgradeModel(Parcel in) {
        this.downUrl = in.readString();
        this.updateInfo = in.readString();
        this.versionName = in.readString();
        this.versionNum = in.readString();
        this.isMandatoryUpdate = in.readByte() != 0;
        this.isUpdate = in.readByte() != 0;
    }

    public static final Creator<UpgradeModel> CREATOR = new Creator<UpgradeModel>() {
        public UpgradeModel createFromParcel(Parcel source) {
            return new UpgradeModel(source);
        }

        public UpgradeModel[] newArray(int size) {
            return new UpgradeModel[size];
        }
    };
}
