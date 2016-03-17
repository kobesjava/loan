package com.qtt.jinrong.bean.app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yanxin on 16/3/4.
 */
public class UpgradeModel implements Parcelable{

    /** 下载地址*/
    private String url = "";
    /** 版本信息*/
    private String versionInfo = "";
    /** 版本号*/
    private String version = "";
    /** 是否强制执行*/
    private boolean ifForced;
    /** 是否更新*/
    private boolean upgrade;

    public boolean needUpgrade() {
        return ifForced || upgrade;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isIfForced() {
        return ifForced;
    }

    public void setIfForced(boolean ifForced) {
        this.ifForced = ifForced;
    }

    public boolean isUpgrade() {
        return upgrade;
    }

    public void setUpgrade(boolean upgrade) {
        this.upgrade = upgrade;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.versionInfo);
        dest.writeString(this.version);
        dest.writeByte(ifForced ? (byte) 1 : (byte) 0);
        dest.writeByte(upgrade ? (byte) 1 : (byte) 0);
    }

    public UpgradeModel() {
    }

    protected UpgradeModel(Parcel in) {
        this.url = in.readString();
        this.versionInfo = in.readString();
        this.version = in.readString();
        this.ifForced = in.readByte() != 0;
        this.upgrade = in.readByte() != 0;
    }

    public static final Creator<UpgradeModel> CREATOR = new Creator<UpgradeModel>() {
        @Override
        public UpgradeModel createFromParcel(Parcel source) {
            return new UpgradeModel(source);
        }

        @Override
        public UpgradeModel[] newArray(int size) {
            return new UpgradeModel[size];
        }
    };
}
