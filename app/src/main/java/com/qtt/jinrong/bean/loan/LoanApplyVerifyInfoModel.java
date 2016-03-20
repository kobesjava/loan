package com.qtt.jinrong.bean.loan;

/**
 * Created by yanxin on 16/3/20.
 */
public class LoanApplyVerifyInfoModel {

    /** 身份*/
    private Integer capacity;

    /** 法人或股东*/
    private Integer corporation;
    /** 企业经营地*/
    private Integer epBuss;
    /** 经营年限*/
    private Integer epPeriod;

    /** 现单位工龄*/
    private Integer currSeniority;
    /** 收入发放方式*/
    private Integer payWay;
    /** 月均总收入*/
    private Integer monthlyIncome;
    /** 社保和公积金*/
    private Integer socialSecurity;

    /** 年龄*/
    private Integer age;
    /** 信用情况*/
    private Integer creInfo;
    /** 逾期情况*/
    private Integer overdue;
    /** 信用卡总额度*/
    private Integer creMoney;
    /** 已使用额度*/
    private Integer creUsed;
    /** 房产信息*/
    private Integer houseInfo;
    /** 房产位置*/
    private Integer district;
    /** 房产抵押*/
    private Integer mortgage;
    /** 车产信息*/
    private Integer car;
    /** 牌照归属地*/
    private Integer carBelong;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public Integer getEpBuss() {
        return epBuss;
    }

    public void setEpBuss(Integer epBuss) {
        this.epBuss = epBuss;
    }

    public Integer getEpPeriod() {
        return epPeriod;
    }

    public void setEpPeriod(Integer epPeriod) {
        this.epPeriod = epPeriod;
    }

    public Integer getCurrSeniority() {
        return currSeniority;
    }

    public void setCurrSeniority(Integer currSeniority) {
        this.currSeniority = currSeniority;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Integer getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Integer socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCreInfo() {
        return creInfo;
    }

    public void setCreInfo(Integer creInfo) {
        this.creInfo = creInfo;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public Integer getCreMoney() {
        return creMoney;
    }

    public void setCreMoney(Integer creMoney) {
        this.creMoney = creMoney;
    }

    public Integer getCreUsed() {
        return creUsed;
    }

    public void setCreUsed(Integer creUsed) {
        this.creUsed = creUsed;
    }

    public Integer getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(Integer houseInfo) {
        this.houseInfo = houseInfo;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getMortgage() {
        return mortgage;
    }

    public void setMortgage(Integer mortgage) {
        this.mortgage = mortgage;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public Integer getCarBelong() {
        return carBelong;
    }

    public void setCarBelong(Integer carBelong) {
        this.carBelong = carBelong;
    }
}
