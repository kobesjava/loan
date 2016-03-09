package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class FinancingDemandsModel {

    private Integer baseStatus;
    private Integer demandStatus;
    private Integer identityStatus;
    private Integer creditStatus;
    private Integer houseStatus;
    private Integer carStatus;
    private Integer assetStatus;

    public boolean isBaseStatus() {
        return baseStatus!=null && baseStatus != 1;
    }

    public boolean isDemandStatus() {
        return demandStatus!=null && demandStatus != 1;
    }

    public boolean isIdentityStatus() {
        return identityStatus!=null && identityStatus!= 1;
    }

    public boolean isCreditStatus() {
        return creditStatus!=null && creditStatus!=1;
    }

    public boolean isHouseStatus() {
        return houseStatus!=null&& houseStatus!=1;
    }

    public boolean isCarStatus() {
        return carStatus!=null&&carStatus!=1;
    }

    public boolean isAssetStatus() {
        return assetStatus!=null && assetStatus!=1;
    }

    public Integer getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(Integer baseStatus) {
        this.baseStatus = baseStatus;
    }

    public Integer getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(Integer demandStatus) {
        this.demandStatus = demandStatus;
    }

    public Integer getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
    }

    public Integer getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(Integer creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Integer getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(Integer houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public Integer getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(Integer assetStatus) {
        this.assetStatus = assetStatus;
    }
}
