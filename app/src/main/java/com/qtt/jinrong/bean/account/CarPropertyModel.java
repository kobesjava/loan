package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class CarPropertyModel {

    /** 是否有车产*/
    private Integer car;

    private String carBrand;

    private String carNo;

    /** 牌照归属地*/
    private Integer carBelong;

    /** 购买价格*/
    private Float carPrice;

    private Integer carValuation;

    /** 车龄*/
    private Integer carAge;

    /** 行驶里程*/
    private Integer carRange;

    private Integer carMortgage;

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getCarBelong() {
        return carBelong;
    }

    public void setCarBelong(Integer carBelong) {
        this.carBelong = carBelong;
    }

    public Float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Float carPrice) {
        this.carPrice = carPrice;
    }

    public Integer getCarValuation() {
        return carValuation;
    }

    public void setCarValuation(Integer carValuation) {
        this.carValuation = carValuation;
    }

    public Integer getCarAge() {
        return carAge;
    }

    public void setCarAge(Integer carAge) {
        this.carAge = carAge;
    }

    public Integer getCarRange() {
        return carRange;
    }

    public void setCarRange(Integer carRange) {
        this.carRange = carRange;
    }

    public Integer getCarMortgage() {
        return carMortgage;
    }

    public void setCarMortgage(Integer carMortgage) {
        this.carMortgage = carMortgage;
    }
}
