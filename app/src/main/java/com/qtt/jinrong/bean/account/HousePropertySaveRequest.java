package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class HousePropertySaveRequest implements IRequest {

    private String userId;
    private int house;
    /** 房产地段*/
    private Integer district;
    /** 地址*/
    private String addr;
    /** 备用房*/
    private Integer spare;
    /** 面积*/
    private Integer area;
    /** 购买总价(万元)*/
    private Integer totalPrice;
    /** 房产现价(元/平方米)*/
    private Integer currPrice;
    /** 房产估值*/
    private Integer evaluation;
    /** 房产抵押/按揭情况*/
    private Integer mortgage;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getSpare() {
        return spare;
    }

    public void setSpare(Integer spare) {
        this.spare = spare;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(Integer currPrice) {
        this.currPrice = currPrice;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getMortgage() {
        return mortgage;
    }

    public void setMortgage(Integer mortgage) {
        this.mortgage = mortgage;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("house",house);
        map.put("district",district);
        map.put("addr", addr);
        map.put("spare",spare);
        map.put("area",area);
        map.put("totalPrice",totalPrice);
        map.put("currPrice",currPrice);
        map.put("evaluation",evaluation);
        map.put("mortgage",mortgage);
        return map;
    }
}
