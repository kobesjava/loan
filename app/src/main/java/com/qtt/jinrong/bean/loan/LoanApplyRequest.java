package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/16.
 */
public class LoanApplyRequest implements IRequest{

    /** 用户ID*/
    private String userId;
    /** 产品ID*/
    private String productId;
    /** 贷款总额*/
    private Integer money;
    /** 贷款期限*/
    private Integer expires;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Integer getCreInfo() {
        return creInfo;
    }

    public void setCreInfo(Integer creInfo) {
        this.creInfo = creInfo;
    }

    public Integer getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(Integer houseInfo) {
        this.houseInfo = houseInfo;
    }

    public Integer getCarBelong() {
        return carBelong;
    }

    public void setCarBelong(Integer carBelong) {
        this.carBelong = carBelong;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("productId",productId);
        map.put("money",money);
        map.put("expires",expires);

        map.put("capacity",capacity);
        //IdentityEnum mEnums = IdentityEnum.find(capacity);
        //if(mEnums.equals(IdentityEnum.企业户) || mEnums.equals(IdentityEnum.个体户)) {
            map.put("corporation",corporation);
            map.put("epBuss",epBuss);
            map.put("epPeriod",epPeriod);
        //} else {
            map.put("currSeniority",currSeniority);
            map.put("payWay",payWay);
            map.put("monthlyIncome",monthlyIncome);
            map.put("socialSecurity",socialSecurity);
        //}

        map.put("age",age);

        map.put("creInfo",creInfo);
        //CreditSituationEnum mEnum = CreditSituationEnum.find(credit);
        //if(mEnum != null && mEnum.equals(CreditSituationEnum.有逾期)) {
            map.put("overdue",overdue);
        //}

        map.put("creMoney",creMoney);
        //CreditTotalLimitEnum ctlEnum = CreditTotalLimitEnum.find(creMoney);
        //if(ctlEnum != null && !ctlEnum.equals(CreditTotalLimitEnum.无信用卡)) {
            map.put("creUsed",creUsed);
        //}

        map.put("houseInfo",houseInfo);
        //HousePropertyEnum hpEnum = HousePropertyEnum.find(house);
        //if(hpEnum != null && !hpEnum.equals(HousePropertyEnum.无房产)) {
            map.put("district",district);
            map.put("mortgage",mortgage);
        //}

        map.put("car",car);
        //CarPropertyEnum cpEnum = CarPropertyEnum.find(car);
        //if(cpEnum != null && cpEnum.equals(CarPropertyEnum.有车产)) {
            map.put("carBelong",carBelong);
        //}

        return map;
    }
}
