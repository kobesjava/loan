package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class FinancingDemands {

    private boolean baseStatus;
    private boolean demandStatus;
    private boolean identityStatus;
    private boolean creditStatus;
    private boolean houseStatus;
    private boolean carStatus;
    private boolean assetStatus;

    public boolean isBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(boolean baseStatus) {
        this.baseStatus = baseStatus;
    }

    public boolean isDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(boolean demandStatus) {
        this.demandStatus = demandStatus;
    }

    public boolean isIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(boolean identityStatus) {
        this.identityStatus = identityStatus;
    }

    public boolean isCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(boolean creditStatus) {
        this.creditStatus = creditStatus;
    }

    public boolean isHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(boolean houseStatus) {
        this.houseStatus = houseStatus;
    }

    public boolean isCarStatus() {
        return carStatus;
    }

    public void setCarStatus(boolean carStatus) {
        this.carStatus = carStatus;
    }

    public boolean isAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(boolean assetStatus) {
        this.assetStatus = assetStatus;
    }
}
