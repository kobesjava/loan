package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class BaseInfoModel {

    private String username;
    private Integer gender;
    private Integer age;
    private String idNumber;
    private String registerProvince;
    private String registerCity;
    private String registerAddr;
    //婚姻情况
    private Integer marriage;
    //配偶月收入
    private Integer spouseIncome;
    //配偶能否出面担保/签字
    private Integer spouseSign;
    //配偶信用情况
    private Integer spouseCredit;
    //配偶逾期情况
    private Integer spouseOverdue;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRegisterProvince() {
        return registerProvince;
    }

    public void setRegisterProvince(String registerProvince) {
        this.registerProvince = registerProvince;
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity;
    }

    public String getRegisterAddr() {
        return registerAddr;
    }

    public void setRegisterAddr(String registerAddr) {
        this.registerAddr = registerAddr;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public Integer getSpouseIncome() {
        return spouseIncome;
    }

    public void setSpouseIncome(Integer spouseIncome) {
        this.spouseIncome = spouseIncome;
    }

    public Integer getSpouseSign() {
        return spouseSign;
    }

    public void setSpouseSign(Integer spouseSign) {
        this.spouseSign = spouseSign;
    }

    public Integer getSpouseCredit() {
        return spouseCredit;
    }

    public void setSpouseCredit(Integer spouseCredit) {
        this.spouseCredit = spouseCredit;
    }

    public Integer getSpouseOverdue() {
        return spouseOverdue;
    }

    public void setSpouseOverdue(Integer spouseOverdue) {
        this.spouseOverdue = spouseOverdue;
    }
}
