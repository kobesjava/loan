package com.android.finance.bean.credit;

/**
 * Created by yanxin on 16/2/24.
 */
public class CreditModel {

    private String id;

    private String url;

    private String name;

    private String desc;

    private int level;

    private String cashPerscent;

    private int applys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCashPerscent() {
        return cashPerscent;
    }

    public void setCashPerscent(String cashPerscent) {
        this.cashPerscent = cashPerscent;
    }

    public int getApplys() {
        return applys;
    }

    public void setApplys(int applys) {
        this.applys = applys;
    }
}
