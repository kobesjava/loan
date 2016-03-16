package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;
import com.qtt.jinrong.enums.HousePropertyEnum;
import com.qtt.jinrong.enums.HousePropertyMortgageSituationEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class HousePropertySaveRequest implements IRequest {

    private String userId;
    /** 房产情况*/
    private Integer houseInfo;
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
    /** 贷款余额*/
    private Integer loanBalance;
    /** 折后空间*/
    private Integer sale;
    /** 月还款额*/
    private Integer monthRepay;
    /** 已还款月数*/
    private Integer repayMonths;

    public Integer getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(Integer houseInfo) {
        this.houseInfo = houseInfo;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(Integer loanBalance) {
        this.loanBalance = loanBalance;
    }

    public Integer getMonthRepay() {
        return monthRepay;
    }

    public void setMonthRepay(Integer monthRepay) {
        this.monthRepay = monthRepay;
    }

    public Integer getRepayMonths() {
        return repayMonths;
    }

    public void setRepayMonths(Integer repayMonths) {
        this.repayMonths = repayMonths;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        map.put("houseInfo",houseInfo);

        HousePropertyEnum hpEnum = HousePropertyEnum.find(houseInfo);
        if(hpEnum == null || hpEnum.equals(HousePropertyEnum.无房产)) return map;

        map.put("district",district);
        map.put("addr", addr);
        map.put("spare",spare);
        map.put("area",area);
        map.put("totalPrice",totalPrice);
        map.put("currPrice",currPrice);
        map.put("evaluation",evaluation);
        map.put("mortgage",mortgage);

        HousePropertyMortgageSituationEnum hpmsEnum = HousePropertyMortgageSituationEnum.find(mortgage);
        if(hpmsEnum == null || hpmsEnum.equals(HousePropertyMortgageSituationEnum.未被抵押无按揭)) {
            return map;
        }

        map.put("loanBalance",loanBalance);
        map.put("sale",sale);
        map.put("monthRepay",monthRepay);
        map.put("repayMonths",repayMonths);

        return map;
    }
}
