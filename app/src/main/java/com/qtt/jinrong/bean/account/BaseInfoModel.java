package com.qtt.jinrong.bean.account;

/**
 * Created by yanxin on 16/3/8.
 */
public class BaseInfoModel {

    private String username;

    private int gender;

    private int age;

    private String idNumber;

    private int registerProvince;

    private int registerCity;

    private String registerAddr;

    private int marriage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getRegisterProvince() {
        return registerProvince;
    }

    public void setRegisterProvince(int registerProvince) {
        this.registerProvince = registerProvince;
    }

    public int getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(int registerCity) {
        this.registerCity = registerCity;
    }

    public String getRegisterAddr() {
        return registerAddr;
    }

    public void setRegisterAddr(String registerAddr) {
        this.registerAddr = registerAddr;
    }

    public int getMarriage() {
        return marriage;
    }

    public void setMarriage(int marriage) {
        this.marriage = marriage;
    }
}
