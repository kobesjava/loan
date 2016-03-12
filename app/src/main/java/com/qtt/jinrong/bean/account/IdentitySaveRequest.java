package com.qtt.jinrong.bean.account;

import com.qtt.jinrong.bean.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanxin on 16/3/8.
 */
public class IdentitySaveRequest implements IRequest {

    private String userId;

    /** 身份*/
    private Integer capacity;
    /** 就职公司类型*/
    private Integer companyType;
    /** 就职公司名称*/
    private String companyName;
    /** 职位*/
    private Integer job;
    /** 现单位工龄*/
    private Integer currSeniority;
    /** 工作地 省*/
    private Integer workProvince;
    /** 工作地 市*/
    private Integer workCity;
    /** 工作详细地址*/
    private String workAddr;
    /** 收入发放方式*/
    private Integer payWay;
    /** 月打卡工资 元*/
    private Integer wages;
    /** 月均总收入 元*/
    private Integer monthlyIncome;
    /** 能否提供收入证明*/
    private Integer verifiableIncome;
    /** 社保公积金*/
    private Integer socialSecurity;
    /** 公积金连续缴纳年限*/
    private Integer accuFundAge;
    /** 社保连续缴纳年限*/
    private Integer socialSecurityAge;
    /** 有无副业*/
    private Integer avocation;

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

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public Integer getCurrSeniority() {
        return currSeniority;
    }

    public void setCurrSeniority(Integer currSeniority) {
        this.currSeniority = currSeniority;
    }

    public Integer getWorkProvince() {
        return workProvince;
    }

    public void setWorkProvince(Integer workProvince) {
        this.workProvince = workProvince;
    }

    public Integer getWorkCity() {
        return workCity;
    }

    public void setWorkCity(Integer workCity) {
        this.workCity = workCity;
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getWages() {
        return wages;
    }

    public void setWages(Integer wages) {
        this.wages = wages;
    }

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Integer getVerifiableIncome() {
        return verifiableIncome;
    }

    public void setVerifiableIncome(Integer verifiableIncome) {
        this.verifiableIncome = verifiableIncome;
    }

    public Integer getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Integer socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Integer getAccuFundAge() {
        return accuFundAge;
    }

    public void setAccuFundAge(Integer accuFundAge) {
        this.accuFundAge = accuFundAge;
    }

    public Integer getSocialSecurityAge() {
        return socialSecurityAge;
    }

    public void setSocialSecurityAge(Integer socialSecurityAge) {
        this.socialSecurityAge = socialSecurityAge;
    }

    public Integer getAvocation() {
        return avocation;
    }

    public void setAvocation(Integer avocation) {
        this.avocation = avocation;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("capacity",capacity);
        map.put("companyType",companyType);
        map.put("companyName",companyName);
        map.put("job",job);
        map.put("currSeniority",currSeniority);
        map.put("workProvince",workProvince);
        map.put("workCity",workCity);
        map.put("workAddr",workAddr);
        map.put("payWay",payWay);
        map.put("wages",wages);
        map.put("monthlyIncome",monthlyIncome);
        map.put("verifiableIncome",verifiableIncome);
        map.put("socialSecurity",socialSecurity);
        map.put("accuFundAge",accuFundAge);
        map.put("socialSecurityAge",socialSecurityAge);
        map.put("avocation",avocation);
        return map;
    }
}
