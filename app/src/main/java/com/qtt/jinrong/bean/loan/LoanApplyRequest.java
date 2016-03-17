package com.qtt.jinrong.bean.loan;

import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.enums.CarPropertyEnum;
import com.qtt.jinrong.enums.CreditSituationEnum;
import com.qtt.jinrong.enums.CreditTotalLimitEnum;
import com.qtt.jinrong.enums.HousePropertyEnum;
import com.qtt.jinrong.enums.IdentityEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yanxin on 16/3/16.
 */
public class LoanApplyRequest implements IRequest{

    /** 用户ID*/
    private String userId;
    /** 贷款总额*/
    private Integer amount;
    /** 贷款期限*/
    private Integer applyLimi;

    /** 身份*/
    private Integer capacity;

    /** 法人或股东*/
    private Integer legal;
    /** 企业经营地*/
    private Integer companyPosition;
    /** 经营年限*/
    private Integer operatorYear;

    /** 现单位工龄*/
    private Integer currSeniority;
    /** 收入发放方式*/
    private Integer payWay;
    /** 月均总收入*/
    private Integer salary;
    /** 社保和公积金*/
    private Integer socialSecurity;

    /** 年龄*/
    private Integer age;
    /** 信用情况*/
    private Integer credit;
    /** 逾期情况*/
    private Integer overdue;
    /** 信用卡总额度*/
    private Integer creMoney;
    /** 已使用额度*/
    private Integer creUsed;
    /** 房产信息*/
    private Integer house;
    /** 房产位置*/
    private Integer district;
    /** 房产抵押*/
    private Integer mortgage;
    /** 车产信息*/
    private Integer car;
    /** 牌照归属地*/
    private Integer linscebelong;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getApplyLimi() {
        return applyLimi;
    }

    public void setApplyLimi(Integer applyLimi) {
        this.applyLimi = applyLimi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getLegal() {
        return legal;
    }

    public void setLegal(Integer legal) {
        this.legal = legal;
    }

    public Integer getCompanyPosition() {
        return companyPosition;
    }

    public void setCompanyPosition(Integer companyPosition) {
        this.companyPosition = companyPosition;
    }

    public Integer getOperatorYear() {
        return operatorYear;
    }

    public void setOperatorYear(Integer operatorYear) {
        this.operatorYear = operatorYear;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
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

    public Integer getLinscebelong() {
        return linscebelong;
    }

    public void setLinscebelong(Integer linscebelong) {
        this.linscebelong = linscebelong;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Integer socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("amount",amount);
        map.put("applyLimi",applyLimi);

        map.put("capacity",capacity);
        //IdentityEnum mEnums = IdentityEnum.find(capacity);
        //if(mEnums.equals(IdentityEnum.企业户) || mEnums.equals(IdentityEnum.个体户)) {
            map.put("legal",legal);
            map.put("companyPosition",companyPosition);
            map.put("operatorYear",operatorYear);
        //} else {
            map.put("currSeniority",currSeniority);
            map.put("payWay",payWay);
            map.put("salary",salary);
            map.put("socialSecurity",socialSecurity);
        //}

        map.put("age",age);

        map.put("credit",credit);
        //CreditSituationEnum mEnum = CreditSituationEnum.find(credit);
        //if(mEnum != null && mEnum.equals(CreditSituationEnum.有逾期)) {
            map.put("overdue",overdue);
        //}

        map.put("creMoney",creMoney);
        //CreditTotalLimitEnum ctlEnum = CreditTotalLimitEnum.find(creMoney);
        //if(ctlEnum != null && !ctlEnum.equals(CreditTotalLimitEnum.无信用卡)) {
            map.put("creUsed",creUsed);
        //}

        map.put("house",house);
        //HousePropertyEnum hpEnum = HousePropertyEnum.find(house);
        //if(hpEnum != null && !hpEnum.equals(HousePropertyEnum.无房产)) {
            map.put("district",district);
            map.put("mortgage",mortgage);
        //}

        map.put("car",car);
        //CarPropertyEnum cpEnum = CarPropertyEnum.find(car);
        //if(cpEnum != null && cpEnum.equals(CarPropertyEnum.有车产)) {
            map.put("linscebelong",linscebelong);
        //}

        return map;
    }
}
