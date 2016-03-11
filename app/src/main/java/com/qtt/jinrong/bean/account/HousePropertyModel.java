package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class HousePropertyModel {

    /** 有无车产*/
    private Integer house;

    /** 房产地段*/
    private Integer district;
    /** 地址*/
    private String addr;
    /** 备用房*/
    private Integer spare;
    /** 面积*/
    private Integer area;
    /** 购买总价(万元)*/
    private Float totalPrice;
    /** 房产现价(元/平方米)*/
    private Integer currPrice;
    /** 房产估值*/
    private Integer evaluation;
    /** 房产抵押/按揭情况*/
    private Integer mortgage;

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
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

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
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
}
