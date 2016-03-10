package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class CarPropertySaveRequest implements IRequest {

    private String userId;

    private Integer car;

    private String carBrand;

    private String carNo;

    private Integer carBelong;

    private Float carPrice;

    private Integer carValuation;

    private Integer carAge;

    private Integer carRange;

    private Integer carMortgage;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("car",car);
        map.put("carBrand",carBrand);
        map.put("carNo",carNo);
        map.put("carBelong",carBelong);
        map.put("carPrice",carPrice);
        map.put("carValuation",carValuation);
        map.put("carAge",carAge);
        map.put("carRange",carRange);
        map.put("carMortgage",carMortgage);
        return map;
    }
}
