package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class BaseInfoModel {

    private String username;

    private Integer gender;

    private Integer age;

    private String idNumber;

    private Integer registerProvince;

    private Integer registerCity;

    private String registerAddr;

    private Integer marriage;

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

    public Integer getRegisterProvince() {
        return registerProvince;
    }

    public void setRegisterProvince(Integer registerProvince) {
        this.registerProvince = registerProvince;
    }

    public Integer getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(Integer registerCity) {
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
}
