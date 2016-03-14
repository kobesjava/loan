package com.qtt.jinrong.bean.credit;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditModel {

    private String id;

    private String thumpImg;

    private String name;

    private String desc;

    private Integer level;

    private String cashPerscent;

    private Integer applys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumpImg() {
        return thumpImg;
    }

    public void setThumpImg(String thumpImg) {
        this.thumpImg = thumpImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCashPerscent() {
        return cashPerscent;
    }

    public void setCashPerscent(String cashPerscent) {
        this.cashPerscent = cashPerscent;
    }

    public Integer getApplys() {
        return applys;
    }

    public void setApplys(Integer applys) {
        this.applys = applys;
    }
}
